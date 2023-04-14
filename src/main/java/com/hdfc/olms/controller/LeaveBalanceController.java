package com.hdfc.olms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.service.ILeaveBalanceService;

@RestController
@RequestMapping("/api/admin/leave-balance")
public class LeaveBalanceController {
	
	@Autowired
	ILeaveBalanceService leaveBalanceService;
	
	@GetMapping("/get-all")
	public List<LeaveBalance> getAllLeaveBalances(){
		return leaveBalanceService.getAllLeaveBalances();
	}
	
	@GetMapping("/get-by-employee/{employeeId}")
	public List<LeaveBalance> getEmployeeLeaveBalances(@PathVariable long employeeId){
		return leaveBalanceService.getEmployeeLeaveBalances(employeeId);
	}
	
	@GetMapping("/get-absentism")
	public List<Employee> getEmployeeAbsentism(){
		return leaveBalanceService.getEmployeAbsentism();
	}

}
