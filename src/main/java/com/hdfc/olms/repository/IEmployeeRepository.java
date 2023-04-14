package com.hdfc.olms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long>{
	
	public Employee findByEmail(String email);

}
