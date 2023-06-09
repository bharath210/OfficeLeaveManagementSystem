package com.hdfc.olms.dto;

import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.utils.enums.LeaveType;

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
public class LeaveBalanceDTO {
	
	private long leaveBalanceId;
	private Employee employee;
	private LeaveType leaveType;
	private long balance;

}
