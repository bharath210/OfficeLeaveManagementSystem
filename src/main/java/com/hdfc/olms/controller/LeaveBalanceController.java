package com.hdfc.olms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.service.ILeaveBalanceService;
import com.hdfc.olms.utils.enums.LeaveType;

@RestController
@RequestMapping("/api/admin/leave-balance")
public class LeaveBalanceController {

	@Autowired
	ILeaveBalanceService leaveBalanceService;

	@PutMapping("/update")
	public LeaveBalance updateLeaveBalance(@RequestParam("employeeId") long emoloyeeId,
			@RequestParam("leaveType") LeaveType leaveType, @RequestParam("balance") long balance) {
		return leaveBalanceService.updateLeaveBalance(emoloyeeId, leaveType, balance);
	}

	@GetMapping("/get-all")
	public List<LeaveBalance> getAllLeaveBalances() {
		return leaveBalanceService.getAllLeaveBalances();
	}

	@GetMapping("/get-by-employee/{employeeId}")
	public List<LeaveBalance> getEmployeeLeaveBalances(@PathVariable long employeeId) {
		return leaveBalanceService.getEmployeeLeaveBalances(employeeId);
	}


	@GetMapping("/Update-employee-absenteeism/{employeeId}")
	public LeaveBalance updateEmployeeAbsenteeism(@PathVariable long employeeId) throws LeaveBalanceNotFoundException, EmployeeNotFoundException {
		return leaveBalanceService.updateEmployeeAbsenteeism(employeeId);
	}
	
	@GetMapping("/get-employee-absenteeism")
	public List<Employee> getEmployeeAbsenteeism(){
		return leaveBalanceService.getEmployeeAbsenteeism();
	}
	

}
