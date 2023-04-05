package com.hdfc.olms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.LeaveBalance;

@Repository
public interface ILeaveBalanceRepository extends JpaRepository<LeaveBalance, Integer> {

}
