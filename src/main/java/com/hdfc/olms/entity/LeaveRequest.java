package com.hdfc.olms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Leave_Requests")
public class LeaveRequest {
	
	@Id
	private int leaveRequestId;
	@ManyToOne
	private Employee employee;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveType;
	private String reason;
	private String status;

}
