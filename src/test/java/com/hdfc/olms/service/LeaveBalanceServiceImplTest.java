package com.hdfc.olms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.utils.enums.LeaveType;
@SpringBootTest
class LeaveBalanceServiceImplTest {
	
	@Autowired
	ILeaveBalanceService leaveBalanceService;

	@Test
	void testAddLeaveBalance() {
		Employee emp = new Employee();
		emp.setEmployeeId(76);
		 LeaveBalanceDTO leaveBalanceDTO = new LeaveBalanceDTO();
		 leaveBalanceDTO.setEmployee(emp);
		 leaveBalanceDTO.setLeaveType(LeaveType.MATERNITY_LEAVE);
		 leaveBalanceDTO.setLeaveBalanceId(120);
		 LeaveBalance lb = leaveBalanceService.addLeaveBalance(leaveBalanceDTO);
		 assertNotNull(lb);
	}

	@Test
	void testUpdateBalance() throws LeaveBalanceNotFoundException {
		LeaveBalance leaveBlance = leaveBalanceService.updateBalance(79, 5);
		assertEquals(5, leaveBlance.getBalance());
	}

	@Test
	void testGetLeaveBalanceById() throws LeaveBalanceNotFoundException {
		LeaveBalance leaveBalance = leaveBalanceService.getLeaveBalanceById(79);
		assertNotNull(leaveBalance);
	}

	@Test
	void testGetEmployeeLeaveBalances() {
		List<LeaveBalance> leaveBalance = leaveBalanceService.getEmployeeLeaveBalances(65);
		assertEquals(3, leaveBalance.size());
	}

	@Test
	void testGetLeaveBalanceByEmployeeAndLeaveType() {
		LeaveBalance leaveBalance = leaveBalanceService.getLeaveBalanceByEmployeeAndLeaveType(76, LeaveType.MATERNITY_LEAVE);
		assertNotNull(leaveBalance);
	}

	@Test
	void testGetAllLeaveBalances() {
		List<LeaveBalance> leaveBalances = leaveBalanceService.getAllLeaveBalances();
		assertTrue(leaveBalances.size() > 3);
	}
}
