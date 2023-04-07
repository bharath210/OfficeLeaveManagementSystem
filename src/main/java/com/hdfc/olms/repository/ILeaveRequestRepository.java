package com.hdfc.olms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.LeaveRequest;

@Repository
public interface ILeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
	
	@Query("select lr from LeaveRequest lr where lr.status = 'PENDING'")
	public List<LeaveRequest> getPendingLeaveRquests();
}
