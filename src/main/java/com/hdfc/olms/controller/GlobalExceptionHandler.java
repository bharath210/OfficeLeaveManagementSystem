package com.hdfc.olms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hdfc.olms.exception.EmployeeNotFoundException;
import com.hdfc.olms.exception.LeaveBalanceNotFoundException;
import com.hdfc.olms.exception.LeaveRequestNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<?> employeeNotFoundExceptionHandler(EmployeeNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LeaveRequestNotFoundException.class)
	public ResponseEntity<String> handleLeaveRequestNotFoundException(LeaveRequestNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LeaveBalanceNotFoundException.class)
	public ResponseEntity<String> handleLeaveBalancetNotFoundException(LeaveBalanceNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> getErrors(ConstraintViolationException ex) {
		List<String> errors = new ArrayList<>();
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations) {
			errors.add(violation.getPropertyPath() + " : " + violation.getMessage());
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
