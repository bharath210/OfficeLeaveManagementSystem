package com.hdfc.olms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.olms.dto.LeaveBalanceDTO;
import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.repository.ILeaveBalanceRepository;

@Service
public class LeaveBalanceServiceImpl implements ILeaveBalanceService{
	
	@Autowired
	ILeaveBalanceRepository leaveBalanceRepo;
	
	@Override
	public LeaveBalance addLeaveBalance(LeaveBalanceDTO leaveBalanceDTO) {
		LeaveBalance leaveBalance = new LeaveBalance();
		leaveBalance.setLeaveBalanceId(leaveBalanceDTO.getLeaveBalanceId());
		leaveBalance.setEmployee(leaveBalanceDTO.getEmployee());
		leaveBalance.setLeaveType(leaveBalanceDTO.getLeaveType());
		leaveBalance.setBalance(leaveBalanceDTO.getBalance());
		
		return leaveBalanceRepo.save(leaveBalance);
	}
	
	@Override
	public LeaveBalance updateLeaveBalance(long leaveBalanceId, long balance) {
		LeaveBalance leaveBalance = getLeaveBalanceById(leaveBalanceId);
		leaveBalance.setBalance(balance);
		return leaveBalanceRepo.save(leaveBalance);
	}

	@Override
	public LeaveBalance getLeaveBalanceById(long leaveBalanceId) {
		
		return leaveBalanceRepo.findById(leaveBalanceId).orElse(null);
	}

//	@Override
//	@Transactional
//	public List<LeaveBalance> getEmployeeLeaveBalances(long employeeId) {
//		
//		return leaveBalanceRepo.getEmployeeLeaveBalances(employeeId);
//	}
//

	


}
