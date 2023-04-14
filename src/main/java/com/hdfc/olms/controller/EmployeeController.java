package com.hdfc.olms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.service.IEmployeeService;

@RestController
@RequestMapping("/api/admin/employees")
public class EmployeeController {
	
	@Autowired
	IEmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

		  Employee employee = employeeService.addEmployee(employeeDTO);
		  return new ResponseEntity<>(employee, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.updateEmployee(employeeDTO);
	}
	
	@GetMapping("/get-all")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/get/{employeeId}")
	public Employee getEmployeeById(@PathVariable long employeeId) throws EmployeeNotFoundException {
		if(!employeeService.isEmployeeExist(employeeId)) {
			throw new EmployeeNotFoundException();
		}
		return employeeService.getEmployeeById(employeeId);
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException{
		if(!employeeService.isEmployeeExist(employeeId)) {
			throw new EmployeeNotFoundException();
		}
		employeeService.deleteEmployeeById(employeeId);
		return new ResponseEntity<>("Employee record deleted", HttpStatus.OK);
	}
	
	@GetMapping("/get-by-email/{email}")
	public Employee findByEmail(@PathVariable String email) {
			
			return employeeService.findByEmail(email);
		}

}
