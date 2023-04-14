package com.hdfc.olms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.service.ILeaveRequestService;
import com.hdfc.olms.utils.enums.LeaveStatusType;

@RestController
@RequestMapping("/api/admin/leave-request")
public class LeaveRequestController {

	@Autowired
	ILeaveRequestService leaveRequestService;
	
	@PostMapping("/apply-leave")
	public LeaveRequest applyForLeave(@RequestBody LeaveRequestDTO leaveRequestDTO) {
		return leaveRequestService.applyForLeave(leaveRequestDTO);
	}

	@PutMapping("/approve-or-reject")
	public LeaveRequest aproveOrRejectLeave(@RequestParam("leaveRequestId") long leaveRequestId, 
			@RequestParam("status") LeaveStatusType status, @RequestParam("comment") String comment) {
		return leaveRequestService.UpdateLeave(leaveRequestId, status, comment);
	}
	
	@GetMapping("/get/pending-leaves/all" )
	public List<LeaveRequest> getPendingLeaves(){
		return leaveRequestService.getPendingLeaveRquests();
	}
	
	@GetMapping("/get/leave-history/by-id/{employeeId}")
	public List<LeaveRequest> getLeaveRequestByEmployeeId(@PathVariable long employeeId){
		return leaveRequestService.getLeaveRequestByEmployeeId(employeeId);
	}
	
	@GetMapping("/get/leave-history/all")
	public List<LeaveRequest> getLeaveHistoryAll(){
		return leaveRequestService.getLeaveHistroyAll();
	}
	
	@GetMapping("/get/leave-status/{leaveRequestId}")
	public LeaveRequest getLeaveStatus(@PathVariable long leaveRequestId) {
		return leaveRequestService.getLeaveStatus(leaveRequestId);
	}
	
	@GetMapping("/get/pending-leaves/{employeeId}")
	public List<LeaveRequest> getPendingLeaveRequestByEmployee(@PathVariable long employeeId){
		return leaveRequestService.getPendingLeaveRquestsByEmployee(employeeId);
	}

}
