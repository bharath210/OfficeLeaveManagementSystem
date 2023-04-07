package com.hdfc.olms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.service.ILeaveRequestService;
import com.hdfc.olms.utils.enums.LeaveStatusType;

@RestController
@RequestMapping("/api/admin/leave-request")
public class AdminLeaveRequestController {

	@Autowired
	ILeaveRequestService leaveRequestService;

	@PutMapping("/approve-or-reject")
	public LeaveRequest aproveOrRejectLeave(@RequestParam("leaveRequestId") long leaveRequestId, 
			@RequestParam("status") LeaveStatusType status, @RequestParam("comment") String comment) {
		return leaveRequestService.UpdateLeave(leaveRequestId, status, comment);
	}
	
	@GetMapping("/get/pending-leaves")
	public List<LeaveRequest> getPendingLeaves(){
		return leaveRequestService.getPendingLeaveRquests();
	}

}
