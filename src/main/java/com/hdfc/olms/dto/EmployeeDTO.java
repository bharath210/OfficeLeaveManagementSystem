package com.hdfc.olms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *@author Bharath Kumar
 *@created 03-Apr-2023
*
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
	
	private long employeeId;
	private String name;
	private String email;
	private String phoneNumber;
	private String department;

}
