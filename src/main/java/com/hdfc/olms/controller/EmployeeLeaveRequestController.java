package com.hdfc.olms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.service.ILeaveRequestService;

@RestController
@RequestMapping("/api/employee/leave-request")
public class EmployeeLeaveRequestController {
	
	@Autowired
	ILeaveRequestService leaveRequestService;
	 
	@PostMapping("/apply-leave")
	public LeaveRequest applyForLeave(@RequestBody LeaveRequestDTO leaveRequestDTO) {
		return leaveRequestService.applyForLeave(leaveRequestDTO);
	}

}
