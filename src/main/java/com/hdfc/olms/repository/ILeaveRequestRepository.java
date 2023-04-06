package com.hdfc.olms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.LeaveRequest;

@Repository
public interface ILeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

}
