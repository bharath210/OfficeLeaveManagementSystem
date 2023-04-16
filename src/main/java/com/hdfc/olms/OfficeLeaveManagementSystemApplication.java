package com.hdfc.olms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hdfc.olms.repository.IEmployeeRepository;
import com.hdfc.olms.repository.ILeaveRequestRepository;
/**
 *@author Bharath Kumar
 *@created 04-Apr-2023
*
 */
@SpringBootApplication
public class OfficeLeaveManagementSystemApplication {
	
	@Autowired
	IEmployeeRepository empRepo;
	
	@Autowired
	ILeaveRequestRepository leaveRequestRepo;

	public static void main(String[] args) {
		SpringApplication.run(OfficeLeaveManagementSystemApplication.class, args);
	}

}
