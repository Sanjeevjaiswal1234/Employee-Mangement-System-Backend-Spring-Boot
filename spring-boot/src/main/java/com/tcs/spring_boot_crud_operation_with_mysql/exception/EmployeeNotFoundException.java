package com.tcs.spring_boot_crud_operation_with_mysql.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
