package com.hdfc.olms.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hdfc.olms.utils.enums.LeaveType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "Leave_Balances")
public class LeaveBalance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long leaveBalanceId;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;
	private long balance;

}
