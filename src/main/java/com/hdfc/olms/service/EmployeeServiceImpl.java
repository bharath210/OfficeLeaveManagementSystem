package com.hdfc.olms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.repository.IEmployeeRepository;
import com.hdfc.olms.repository.ILeaveBalanceRepository;
import com.hdfc.olms.utils.JasperReportUtil;
import com.hdfc.olms.utils.LeaveBalanceConstants;
import com.hdfc.olms.utils.enums.LeaveType;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@Autowired
	ILeaveBalanceService leaveBalanceService;
	
	@Autowired
	ILeaveBalanceRepository leaveBalanceRepo;
	@Override
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setDepartment(employeeDTO.getDepartment());
		
		Employee savedEmployee = employeeRepo.save(employee);
		if(savedEmployee != null) {
			LeaveBalanceDTO casualLeaveBalance = new LeaveBalanceDTO();
			casualLeaveBalance.setEmployee(savedEmployee);
			casualLeaveBalance.setLeaveType(LeaveType.CASUAL_LEAVE);
			casualLeaveBalance.setBalance(LeaveBalanceConstants.CASUAL_LEAVE_BALANCE);
			
			LeaveBalanceDTO sickLeaveBalance = new LeaveBalanceDTO();
			sickLeaveBalance.setEmployee(savedEmployee);
			sickLeaveBalance.setLeaveType(LeaveType.SICK_LEAVE);
			sickLeaveBalance.setBalance(LeaveBalanceConstants.SICK_LEAVE_BALANCE);
			
			LeaveBalanceDTO earnedLeaveBalance = new LeaveBalanceDTO();
			earnedLeaveBalance.setEmployee(savedEmployee);
			earnedLeaveBalance.setLeaveType(LeaveType.ANUAL_LEAVE);
			earnedLeaveBalance.setBalance(LeaveBalanceConstants.ANUAL_LEAVE_BALANCE);
			
			leaveBalanceService.addLeaveBalance(casualLeaveBalance);
			log.info(casualLeaveBalance + " is inserted");
			
			leaveBalanceService.addLeaveBalance(sickLeaveBalance);
			log.info(sickLeaveBalance + " is inserted");
			
			leaveBalanceService.addLeaveBalance(earnedLeaveBalance);
			log.info(earnedLeaveBalance + " is inserted");
		}
		
		return savedEmployee;
	}

	@Override
	public Employee updateEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setDepartment(employeeDTO.getDepartment());
		
		return employeeRepo.save(employee);
	}

	@Override
	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException {
		if(!employeeRepo.existsById(employeeId)) {
			throw new EmployeeNotFoundException("Could not find Employee by employeeId : " +  employeeId);
		}
			
		return employeeRepo.findById(employeeId).orElse(null);
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		try {
			JasperReportUtil.generateHtmlReport(employees, "employee");
			log.info("Employee reports generated");
		} catch (FileNotFoundException | JRException e) {
			
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public void deleteEmployeeById(long employeeId) throws EmployeeNotFoundException {
		if(!employeeRepo.existsById(employeeId)) {
			throw new EmployeeNotFoundException("Could not find Employee by employeeId : " +  employeeId);
		}
		List<LeaveBalance> list = leaveBalanceService.getEmployeeLeaveBalances(employeeId);
		for(LeaveBalance leaveBalance : list) {
			leaveBalanceRepo.delete(leaveBalance);
		}
		employeeRepo.deleteById(employeeId);

	}

	@Override
	public Employee findByEmail(String email) throws EmployeeNotFoundException {
		
		Employee employee =  employeeRepo.findByEmail(email);
		if(employee == null) {
			throw new EmployeeNotFoundException("Could not find Employee by email : " +  email);
		}
		return employee;
	}
	

} 
