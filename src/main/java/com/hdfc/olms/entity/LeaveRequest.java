package com.hdfc.olms.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hdfc.olms.utils.enums.LeaveStatusType;
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
@Table(name = "Leave_Requests")
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long leaveRequestId;
	@NotNull(message = "Please provide employee id")
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	@NotNull(message = "Please enter Leave start date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@NotNull(message = "Please enter Leave end date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	@NotNull(message = "Please select Leave type")
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;
	@NotNull(message = "Please specify leave reason")
	private String reason;
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private LeaveStatusType status;
	private String comment;

}
