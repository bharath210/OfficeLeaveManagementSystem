package com.hdfc.olms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.repository.ILeaveRequestRepository;
import com.hdfc.olms.utils.enums.LeaveStatusType;

@Service
public class LeaveRequestServiceImpl implements ILeaveRequestService {
	
	@Autowired
	ILeaveRequestRepository leaveRequestRepo;

	@Override
	public LeaveRequest applyForLeave(LeaveRequestDTO leaveRequestDTO) {
		LeaveRequest leaveRequest = new LeaveRequest();
		
		Employee employee = new Employee();
		employee.setEmployeeId(leaveRequestDTO.getEmployee().getEmployeeId());
		
		leaveRequest.setLeaveRequestId(leaveRequestDTO.getLeaveRequestId());
//		leaveRequest.setEmployee(leaveRequestDTO.getEmployee());
		leaveRequest.setEmployee(employee);
		leaveRequest.setStartDate(leaveRequestDTO.getStartDate());
		leaveRequest.setEndDate(leaveRequestDTO.getEndDate());
		leaveRequest.setReason(leaveRequestDTO.getReason());
		leaveRequest.setLeaveType(leaveRequestDTO.getLeaveType());
		leaveRequest.setStatus(LeaveStatusType.PENDING);
		leaveRequest.setComment(leaveRequestDTO.getComment());
		
		return leaveRequestRepo.save(leaveRequest);
	}

	@Override
	@Transactional
	public List<LeaveRequest> getPendingLeaveRquests() {
		return leaveRequestRepo.getPendingLeaveRquests();
	}

	@Override
	public LeaveRequest getLeaveRequestById(long leaveRequestId) {
		
		return leaveRequestRepo.findById(leaveRequestId).orElse(null);
	}

	@Override
	public LeaveRequest UpdateLeave(long leaveRequestId, LeaveStatusType status, String comment) {
		LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);
		leaveRequest.setStatus(status);
		leaveRequest.setComment(comment);
		return leaveRequestRepo.save(leaveRequest);
	}

}
