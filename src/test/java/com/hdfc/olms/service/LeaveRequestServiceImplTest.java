package com.hdfc.olms.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.olms.dto.LeaveRequestDTO;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.utils.enums.LeaveType;

@SpringBootTest
class LeaveRequestServiceImplTest {
	
	@Autowired
	ILeaveRequestService leaveRequestService;
	
	@Autowired
	IEmployeeService employeeService;

	@Test
	void testApplyForLeave() throws EmployeeNotFoundException {
		LeaveRequestDTO leaveRequest = new LeaveRequestDTO();

		leaveRequest.setEmployee(employeeService.getEmployeeById(76));
		leaveRequest.setStartDate(LocalDate.parse("2023-04-16"));
		leaveRequest.setEndDate(LocalDate.parse("2023-04-17"));
		leaveRequest.setLeaveType(LeaveType.CASUAL_LEAVE);
		leaveRequest.setReason("Going home");
		LeaveRequest leave = leaveRequestService.applyForLeave(leaveRequest);
		assertNotNull(leave);
	}

	@Test
	void testGetPendingLeaveRquests() {
		List<LeaveRequest> leaveRequests = leaveRequestService.getPendingLeaveRquests();
		assertTrue(leaveRequests.size() > 1);
	}


	@Test
	void testGetLeaveRequestByEmployeeId() {
		List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestByEmployeeId(46);
		assertNotNull(leaveRequests);
	}

	@Test
	void testGetLeaveHistroyAll() {
		List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveHistroyAll();
		assertTrue(leaveRequests.size() > 2);
	}



}
