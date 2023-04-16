package com.hdfc.olms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.olms.dto.EmployeeDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.exception.EmployeeNotFoundException;

@SpringBootTest
class EmployeeServiceImplTest {
	
	@Autowired
	IEmployeeService employeeService;

	@Test
	void testAddEmployee() {
		EmployeeDTO employee = new EmployeeDTO();
		employee.setName("Hasin");
		employee.setEmail("hasini@gmail.com");
		employee.setPhoneNumber("+91 8762787367");
		employee.setDepartment("HR");
		
		Employee emp = employeeService.addEmployee(employee);
		assertNotNull(emp);
		
	}

	@Test
	void testGetEmployeeById() throws EmployeeNotFoundException {
		Employee employee =  employeeService.getEmployeeById(42);
		assertEquals("bharath@gmail.com", employee.getEmail());
	}



	@Test
	void testFindByEmail() throws EmployeeNotFoundException {
		Employee employee = employeeService.findByEmail("bharath@gmail.com");
		assertEquals(42, employee.getEmployeeId());
	}

}
