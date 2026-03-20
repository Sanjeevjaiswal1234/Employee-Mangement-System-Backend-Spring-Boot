package com.tcs.spring_boot_crud_operation_with_mysql.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeDto {
	
	
	private Long id;
	@NotBlank(message = "Name should not be blank")
	@Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
	private String name;
	@Email(message = "email is is Not format")
	private String email;
	private String department;
	
	public EmployeeDto() {
		
	}
	
	public Long getId( ) {
		
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
		
	}

	public String getName( ) {
		
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		
	}

	public String getEmail( ) {
		
		return email;
	}
	
	public void setEmail(String email) {
		this.email =email;
		
	}

	public String getDepartment( ) {
		
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
		
	}
	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", email=" + email + ", department=" + department + "]";
	}





}
