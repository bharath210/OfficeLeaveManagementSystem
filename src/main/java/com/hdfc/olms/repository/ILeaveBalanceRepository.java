package com.hdfc.olms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.LeaveBalance;
import com.hdfc.olms.utils.enums.LeaveType;

@Repository
public interface ILeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

	@Query("select lb from LeaveBalance lb where lb.employee.employeeId = :employeeId")
	public List<LeaveBalance> getEmployeeLeaveBalances(@Param("employeeId") long employeeId);

	@Query("select lb from LeaveBalance lb where lb.employee.employeeId = :employeeId and lb.leaveType = :leaveType")
	public LeaveBalance getLeaveBalanceByEmployeeAndLeaveType(@Param("employeeId") long employeeId,
			@Param("leaveType") LeaveType leaveType);
	
	@Query("select sum(lb.balance) from LeaveBalance lb where lb.employee.employeeId = :employeeId")
	public Integer getTotalLeaveBalanceByEmployeeId(@Param("employeeId") long employeeId);
}
