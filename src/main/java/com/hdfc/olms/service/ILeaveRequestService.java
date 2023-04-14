package com.hdfc.olms.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.utils.enums.LeaveStatusType;

public interface ILeaveRequestService {
	public LeaveRequest applyForLeave(LeaveRequestDTO leaveRequestDTO);
	public LeaveRequest UpdateLeave(long leaveRequestId, LeaveStatusType status, String comment);
	public List<LeaveRequest> getPendingLeaveRquests();
	public LeaveRequest getLeaveRequestById(long leaveRequestId);
	public List<LeaveRequest> getLeaveRequestByEmployeeId(long employeeId);
	public List<LeaveRequest> getLeaveHistroyAll();
	public LeaveRequest getLeaveStatus(long leaveRequestId);
	public List<LeaveRequest> getPendingLeaveRquestsByEmployee(long employeeId);
}
