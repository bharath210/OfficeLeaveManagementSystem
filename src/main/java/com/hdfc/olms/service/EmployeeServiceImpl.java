package com.hdfc.olms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.repository.IEmployeeRepository;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	IEmployeeRepository employeeRepo;
	
	@Override
	public Employee addEmployee(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		employee.setEmployeeId(employeeDTO.getEmployeeId());
		employee.setName(employeeDTO.getName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setDepartment(employeeDTO.getDepartment());
		
		return employeeRepo.save(employee);
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

}
