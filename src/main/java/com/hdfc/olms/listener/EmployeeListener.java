package com.hdfc.olms.listener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;

import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.service.ILeaveBalanceService;
import com.hdfc.olms.utils.LeaveBalanceConstants;
import com.hdfc.olms.utils.enums.LeaveType;

public class EmployeeListener {
	
	@Autowired
	ILeaveBalanceService service;
	
	@PersistenceContext
	private EntityManager 	entityManager;
	
	@PostPersist
	public void createNewLeaveBalance(Employee employee) {
		
		LeaveBalanceDTO leaveBalance = new LeaveBalanceDTO();
		leaveBalance.setEmployee(employee);
		leaveBalance.setLeaveType(LeaveType.CASUAL_LEAVE);
		leaveBalance.setBalance(LeaveBalanceConstants.CASUAL_LEAVE_BALANCE);
		service.addLeaveBalance(leaveBalance);
//		entityManager.persist(leaveBalance);
	}

}
