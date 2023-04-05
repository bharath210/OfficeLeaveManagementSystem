package com.hdfc.olms;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hdfc.olms.entity.Employee;
import com.hdfc.olms.entity.LeaveRequest;
import com.hdfc.olms.repository.IEmployeeRepository;
import com.hdfc.olms.repository.ILeaveRequestRepository;
import com.hdfc.olms.utils.enums.LeaveStatusType;
import com.hdfc.olms.utils.enums.LeaveType;

@SpringBootApplication
public class OfficeLeaveManagementSystemApplication {
	
	@Autowired
	IEmployeeRepository empRepo;
	
	@Autowired
	ILeaveRequestRepository leaveRequestRepo;

	public static void main(String[] args) {
		SpringApplication.run(OfficeLeaveManagementSystemApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner entityTest() {
		return args -> {
			Employee e1 = new Employee();
			e1.setEmployeeId(101);
			e1.setName("Bharath");
			e1.setEmail("bharath@gmail.com");
			e1.setPhoneNumber("87654328");
			e1.setDepartment("Developer");
			empRepo.save(e1);
			
			LeaveRequest r1 = new LeaveRequest();
			r1.setLeaveRequestId(201);
			r1.setEmployee(e1);
			r1.setLeaveType(LeaveType.CASUAL_LEAVE);
			r1.setStartDate(LocalDate.now());
			r1.setEndDate(LocalDate.now());
			r1.setReason("Marriege");
			r1.setStatus(LeaveStatusType.PENDING);
			leaveRequestRepo.save(r1);
		};
	}

}
