package com.hdfc.olms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.LeaveBalance;

@Repository
public interface ILeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
	
//	@Query("select lb from LeaveBalance lb where lb.employee_id = :employeeId")
//	public List<LeaveBalance> getEmployeeLeaveBalances(@Param("employeeId") long employeeId);
}
