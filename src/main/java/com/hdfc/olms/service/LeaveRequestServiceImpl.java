package com.hdfc.olms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.exception.LeaveRequestNotFoundException;
import com.hdfc.olms.repository.ILeaveRequestRepository;
import com.hdfc.olms.utils.enums.LeaveStatusType;
import com.hdfc.olms.utils.enums.LeaveType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeaveRequestServiceImpl implements ILeaveRequestService {

	@Autowired
	ILeaveRequestRepository leaveRequestRepo;

	@Autowired
	ILeaveBalanceService leaveBalanceService;

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
	public LeaveRequest getLeaveRequestById(long leaveRequestId) throws LeaveRequestNotFoundException {
		if(!leaveRequestRepo.existsById(leaveRequestId)) {
			throw new LeaveRequestNotFoundException("Could not find Leave Request by leaveRequestId : " + leaveRequestId);
		}

		return leaveRequestRepo.findById(leaveRequestId).orElse(null);
	}

	@Override
	public LeaveRequest UpdateLeave(long leaveRequestId, LeaveStatusType status, String comment) throws LeaveRequestNotFoundException, LeaveBalanceNotFoundException {
		LeaveRequest leaveRequest = getLeaveRequestById(leaveRequestId);
		leaveRequest.setStatus(status);
		leaveRequest.setComment(comment);
		LeaveRequest updatedLeaveRequest = leaveRequestRepo.save(leaveRequest);

		long duration = updatedLeaveRequest.getEndDate().getDayOfYear()
				- updatedLeaveRequest.getStartDate().getDayOfYear()+1;
		log.info("Duration of the leave : " + duration);

		// update leave balance based on leave type into leave balance table
		if ((updatedLeaveRequest.getStatus().equals(LeaveStatusType.APPROVED))
				&& (updatedLeaveRequest.getLeaveType().equals(LeaveType.CASUAL_LEAVE)
						|| updatedLeaveRequest.getLeaveType().equals(LeaveType.ANUAL_LEAVE)
						|| updatedLeaveRequest.getLeaveType().equals(LeaveType.SICK_LEAVE))) {
			LeaveBalance leaveBalance = leaveBalanceService.getLeaveBalanceByEmployeeAndLeaveType(
					updatedLeaveRequest.getEmployee().getEmployeeId(), updatedLeaveRequest.getLeaveType());
			long balance = leaveBalance.getBalance() - duration;
			LeaveBalance updateLeaveBalance = leaveBalanceService.updateBalance(leaveBalance.getLeaveBalanceId(),
					balance);
			log.info(updateLeaveBalance + " is updated");
		}

		return updatedLeaveRequest;
	}

	@Override
	@Transactional
	public List<LeaveRequest> getLeaveRequestByEmployeeId(long employeeId) {

		return leaveRequestRepo.getLeaveHistoryByEmployeeId(employeeId);
	}

	@Override
	@Transactional
	public List<LeaveRequest> getLeaveHistroyAll() {

		return leaveRequestRepo.getLeaveHistroyAll();
	}

	@Override
	@Transactional
	public List<LeaveRequest> getPendingLeaveRquestsByEmployee(long employeeId) {

		return leaveRequestRepo.getPendingLeaveRquestsByEmployee(employeeId);
	}

	@Override
	public void deleteLeaveRequest(long leaveRequestId) throws LeaveRequestNotFoundException {
		if(!leaveRequestRepo.existsById(leaveRequestId)) {
			throw new LeaveRequestNotFoundException("Could not find Leave Request by leaveRequestId : " + leaveRequestId);
		}
		leaveRequestRepo.deleteById(leaveRequestId);
	}


}
