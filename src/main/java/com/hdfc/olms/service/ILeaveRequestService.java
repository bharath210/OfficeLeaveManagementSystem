package com.hdfc.olms.service;

import java.util.List;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.utils.enums.LeaveStatusType;

public interface ILeaveRequestService {
	public LeaveRequest applyForLeave(LeaveRequestDTO leaveRequestDTO);
	public LeaveRequest UpdateLeave(long leaveRequestId, LeaveStatusType status, String comment);
	public List<LeaveRequest> getPendingLeaveRquests();
	public LeaveRequest getLeaveRequestById(long leaveRequestId);
}
