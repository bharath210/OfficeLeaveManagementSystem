package com.hdfc.olms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
	
	private int employeeId;
	private String name;
	private String email;
	private String phoneNumber;
	private String department;

}
