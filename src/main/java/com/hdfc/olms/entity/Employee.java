package com.hdfc.olms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	@NotEmpty(message = "Please enter name")
	private String name;
	@NotNull
	@Email(message = "Please enter valid email")
	private String email;
	@NotNull
	@Pattern(regexp = "\\+91 [6-9][0-9]{9}", message = "Please enter +91 followed by 10 digit phone number")
	private String phoneNumber;
	@NotNull(message = "Please enter department name")
	private String department;

}
