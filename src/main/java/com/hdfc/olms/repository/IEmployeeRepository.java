package com.hdfc.olms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.olms.entity.Employee;
/**
 *@author Bharath Kumar
 *@created 04-Apr-2023
*
 */
@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long>{
	
	public Employee findByEmail(String email);

}
