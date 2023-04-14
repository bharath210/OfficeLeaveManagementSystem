package com.hdfc.olms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.LeaveRequest;

@Repository
public interface ILeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
	
	@Query("select lr from LeaveRequest lr where lr.status = 'PENDING'")
	public List<LeaveRequest> getPendingLeaveRquests();
	
	@Query("select lr from LeaveRequest lr where lr.status = 'PENDING' and lr.employee.employeeId = :employeeId")
	public List<LeaveRequest> getPendingLeaveRquestsByEmployee(@Param("employeeId") long employeeId);
	
	@Query("select lr from LeaveRequest lr where lr.employee.employeeId = :employeeId and lr.status = 'APPROVED' order by lr.endDate DESC")
	public List<LeaveRequest> getLeaveHistoryByEmployeeId(@Param("employeeId") long employeeId);
	
	@Query("select lr from LeaveRequest lr where lr.status = 'APPROVED' order by lr.endDate DESC")
	public List<LeaveRequest> getLeaveHistroyAll();
	
}
