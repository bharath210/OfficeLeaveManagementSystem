package com.hdfc.olms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.repository.IEmployeeRepository;
import com.hdfc.olms.utils.LeaveBalanceConstants;
import com.hdfc.olms.utils.enums.LeaveType;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@Autowired
	ILeaveBalanceService leaveBalanceService;
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
			earnedLeaveBalance.setLeaveType(LeaveType.EARNED_LEAVE);
			earnedLeaveBalance.setBalance(LeaveBalanceConstants.EARNED_LEAVE_BALANCE);
			
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
	public Employee getEmployeeById(long employeeId) {
			
		return employeeRepo.findById(employeeId).orElse(null);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepo.findAll();
	}

	@Override
	public void deleteEmployeeById(long employeeId) {
		employeeRepo.deleteById(employeeId);

	}

	@Override
	public boolean isEmployeeExist(long employeeId) {
		
		return employeeRepo.existsById(employeeId);
	}

	@Override
	public Employee findByEmail(String email) {
		
		return employeeRepo.findByEmail(email);
	}

}
