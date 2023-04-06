package com.hdfc.olms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
	private long employeeId;
	@NotEmpty(message = "Please enter name")
	private String name;
	@NotNull
	@Email(message = "Please enter valid email")
	private String email;
	@NotEmpty
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Please enter 10 digit phone number")
	private String phoneNumber;
	@NotEmpty(message = "Please enter department name")
	private String department;

}
