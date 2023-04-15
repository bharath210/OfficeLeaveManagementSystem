package com.hdfc.olms.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.exception.LeaveRequestNotFoundException;
import com.hdfc.olms.utils.enums.LeaveStatusType;

public interface ILeaveRequestService {
	public LeaveRequest applyForLeave(LeaveRequestDTO leaveRequestDTO);
	public LeaveRequest UpdateLeave(long leaveRequestId, LeaveStatusType status, String comment) throws LeaveRequestNotFoundException, LeaveBalanceNotFoundException;
	public List<LeaveRequest> getPendingLeaveRquests();
	public LeaveRequest getLeaveRequestById(long leaveRequestId) throws LeaveRequestNotFoundException;
	public List<LeaveRequest> getLeaveRequestByEmployeeId(long employeeId);
	public List<LeaveRequest> getLeaveHistroyAll();
	public List<LeaveRequest> getPendingLeaveRquestsByEmployee(long employeeId);
	public void deleteLeaveRequest(long leaveRequestId) throws LeaveRequestNotFoundException;
}
