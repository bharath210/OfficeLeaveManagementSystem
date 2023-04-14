package com.hdfc.olms.service;

import java.util.List;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.entity.Employee;

public interface IEmployeeService {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);
	public Employee updateEmployee(EmployeeDTO employeeDTO);
	public Employee getEmployeeById(long employeeId);
	public List<Employee> getAllEmployees();
	public void deleteEmployeeById(long employeeId);
	public boolean isEmployeeExist(long employeeId);
	public Employee findByEmail(String email);

}
