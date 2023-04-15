package com.hdfc.olms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.repository.IEmployeeRepository;
import com.hdfc.olms.repository.ILeaveBalanceRepository;
import com.hdfc.olms.utils.enums.LeaveType;

@Service
public class LeaveBalanceServiceImpl implements ILeaveBalanceService{
	
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

		 return leaveBalanceRepo.findAll();
		}

	@Override
	@Transactional
	public List<Employee> getEmployeAbsentism() {
		List<Employee> list = new ArrayList<>();
		List<Employee> employees = employeeRepo.findAll();
		for(Employee employee : employees) {
			Integer balance = leaveBalanceRepo.getTotalLeaveBalanceByEmployeeId(employee.getEmployeeId());
			if(balance != null) {
				if(balance.intValue() < 0) {
					list.add(employee);
				}
			}
			
		}
		return list;
	}

	@Override
	public LeaveBalance updateLeaveBalance(long emplpoyeeId, LeaveType leaveType, long balance) {
		LeaveBalance leaveBalance = leaveBalanceRepo.getLeaveBalanceByEmployeeAndLeaveType(emplpoyeeId, leaveType);
		leaveBalance.setBalance(balance);
		
		return leaveBalanceRepo.save(leaveBalance);
	}


}
