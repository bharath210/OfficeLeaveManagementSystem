package com.hdfc.olms.service;

import java.util.List;

import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.utils.enums.LeaveType;

public interface ILeaveBalanceService {
	
	public LeaveBalance addLeaveBalance(LeaveBalanceDTO leaveBalanceDTO);
	public LeaveBalance updateLeaveBalance(long leaveBalanceId, long balance);
	public LeaveBalance getLeaveBalanceById(long leaveBalanceId);
	public List<LeaveBalance> getEmployeeLeaveBalances(long employeeId);
	public List<LeaveBalance> getAllLeaveBalances();
	public LeaveBalance getLeaveBalanceByEmployeeAndLeaveType( long employeeId, LeaveType leaveType );
	public List<Employee> getEmployeAbsentism();
	
	
}
