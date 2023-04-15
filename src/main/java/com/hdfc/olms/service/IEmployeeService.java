package com.hdfc.olms.service;

import java.util.List;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.exception.EmployeeNotFoundException;

public interface IEmployeeService {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);
	public Employee updateEmployee(EmployeeDTO employeeDTO);
	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException;
	public List<Employee> getAllEmployees();
	public void deleteEmployeeById(long employeeId) throws EmployeeNotFoundException;
	public Employee findByEmail(String email) throws EmployeeNotFoundException;

}
