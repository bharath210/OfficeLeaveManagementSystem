package com.hdfc.olms.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hdfc.olms.utils.enums.LeaveType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Leave_Balances")
public class LeaveBalance {
	
	@Id
	private int leaveBalanceId;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;
	private int balance;

}
