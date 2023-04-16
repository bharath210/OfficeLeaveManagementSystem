package com.hdfc.olms.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.exception.EmployeeNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface IEmployeeService {
	
	public Employee addEmployee(EmployeeDTO employeeDTO);
	public Employee updateEmployee(EmployeeDTO employeeDTO);
	public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException;
	public List<Employee> getAllEmployees();
	public void deleteEmployeeById(long employeeId) throws EmployeeNotFoundException;
	public Employee findByEmail(String email) throws EmployeeNotFoundException;

}
