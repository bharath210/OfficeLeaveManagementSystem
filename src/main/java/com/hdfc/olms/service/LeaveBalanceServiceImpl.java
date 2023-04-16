package com.hdfc.olms.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.repository.IEmployeeRepository;
import com.hdfc.olms.repository.ILeaveBalanceRepository;
import com.hdfc.olms.utils.JasperReportUtil;
import com.hdfc.olms.utils.enums.LeaveType;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
@Slf4j
@Service
public class LeaveBalanceServiceImpl implements ILeaveBalanceService{
	List<Long> employees;
	public LeaveBalanceServiceImpl() {
		employees = new ArrayList<Long>();
	}
	
	@Autowired
	ILeaveBalanceRepository leaveBalanceRepo;
	
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@Override
	@Transactional
	public LeaveBalance addLeaveBalance(LeaveBalanceDTO leaveBalanceDTO) {
		LeaveBalance leaveBalance = new LeaveBalance();
		leaveBalance.setLeaveBalanceId(leaveBalanceDTO.getLeaveBalanceId());
		leaveBalance.setEmployee(leaveBalanceDTO.getEmployee());
		leaveBalance.setLeaveType(leaveBalanceDTO.getLeaveType());
		leaveBalance.setBalance(leaveBalanceDTO.getBalance());
		
		return leaveBalanceRepo.save(leaveBalance);
	}
	
	@Override
	public LeaveBalance updateBalance(long leaveBalanceId, long balance) throws LeaveBalanceNotFoundException {
		LeaveBalance leaveBalance = getLeaveBalanceById(leaveBalanceId);
		leaveBalance.setBalance(balance);
		return leaveBalanceRepo.save(leaveBalance);
	}

	@Override
	public LeaveBalance getLeaveBalanceById(long leaveBalanceId) throws LeaveBalanceNotFoundException {
		if(!leaveBalanceRepo.existsById(leaveBalanceId)) {
			throw new LeaveBalanceNotFoundException("Leave Balance record not found with leaveBalanceId : " + leaveBalanceId);
		}
		return leaveBalanceRepo.findById(leaveBalanceId).orElse(null);
	}

	@Override
	@Transactional
	public List<LeaveBalance> getEmployeeLeaveBalances(long employeeId) {
		
		return leaveBalanceRepo.getEmployeeLeaveBalances(employeeId);
	}

	@Override
	@Transactional
	public LeaveBalance getLeaveBalanceByEmployeeAndLeaveType(long employeeId, LeaveType leaveType) {
		
		return leaveBalanceRepo.getLeaveBalanceByEmployeeAndLeaveType(employeeId, leaveType);
	}
	
	@Override
	public List<LeaveBalance> getAllLeaveBalances(){
		List<LeaveBalance> leaveBalances = leaveBalanceRepo.findAll();
		try {
			JasperReportUtil.generateHtmlReport(leaveBalances, "leave_balance");
			log.info("Leave Balance Report Generated");
		} catch (FileNotFoundException | JRException e) {
			
			e.printStackTrace();
		}
		 return leaveBalances;
		}
	
	

	@Override
	public LeaveBalance updateEmployeeAbsenteeism(long employeeId) throws LeaveBalanceNotFoundException, EmployeeNotFoundException {
		if(!employeeRepo.existsById(employeeId)) {
			throw new EmployeeNotFoundException("Could not find employee by employeeId : " + employeeId);
		}
		employees.add(employeeId);
		LeaveBalance leaveBalance = getLeaveBalanceByEmployeeAndLeaveType(employeeId, LeaveType.CASUAL_LEAVE);
		return updateBalance(leaveBalance.getLeaveBalanceId(), leaveBalance.getBalance()-1);
	}
	

	@Override
	public List<Employee> getEmployeeAbsenteeism() {
		List<Employee> empList = new ArrayList<>();
		for(Long id : employees) {
			Employee emp = employeeRepo.findById(id).orElse(null);
			empList.add(emp);
		}
		try {
			JasperReportUtil.generateHtmlReport(empList, "employee_absenteeism");
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
		}
		return empList;
	}

	@Override
	public LeaveBalance updateLeaveBalance(long emplpoyeeId, LeaveType leaveType, long balance) {
		LeaveBalance leaveBalance = leaveBalanceRepo.getLeaveBalanceByEmployeeAndLeaveType(emplpoyeeId, leaveType);
		leaveBalance.setBalance(balance);
		
		return leaveBalanceRepo.save(leaveBalance);
	}



}
