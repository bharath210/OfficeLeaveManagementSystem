package com.hdfc.olms.dto;

import java.time.LocalDate;

import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.utils.enums.LeaveStatusType;
import com.hdfc.olms.utils.enums.LeaveType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LeaveRequestDTO {
	
	private long leaveRequestId;
	private Employee employee;
	private LocalDate startDate;
	private LocalDate endDate;
	private LeaveType leaveType;
	private String reason;
	private LeaveStatusType status;

}
