package com.hdfc.olms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "Leave_Balances")
public class LeaveBalance {
	
	@Id
	private int leaveBalanceId;
	@ManyToOne
	private Employee employee;
	private String leaveType;
	private int balance;

}
