package com.hdfc.olms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "Employees")
public class Employee {
	
	@Id
	private int employeeId;
	private String name;
	private String email;
	private String phoneNumber;
	private String department;

}
