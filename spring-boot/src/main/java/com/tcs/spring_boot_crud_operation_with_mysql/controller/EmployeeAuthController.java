package com.tcs.spring_boot_crud_operation_with_mysql.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.spring_boot_crud_operation_with_mysql.dao.EmployeeDao;
import com.tcs.spring_boot_crud_operation_with_mysql.dto.EmployeeDto;
import com.tcs.spring_boot_crud_operation_with_mysql.entity.Employee;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RequestMapping("/auth/employee")
@RestController
public class EmployeeAuthController {
	
	private final EmployeeDao dao;
	
	public EmployeeAuthController(EmployeeDao dao) {
		
		       super();
		       this.dao= dao;
	}
	
	@PostMapping(value = "/saveEmployeeDto")
	public ResponseEntity<?> saveEmployeeDtoController(@RequestBody @Valid EmployeeDto employeeDto ){
		
		Employee employee = new Employee();
		
		employee.setId(employeeDto.getId());
		employee.setName(employeeDto.getName());
		employee.setEmail(employeeDto.getEmail());
		employee.setDepartment(employeeDto.getDepartment());
		
		HashMap<String , Object> response = new HashMap<String ,Object>();
		
		Employee employee2 = dao.SaveEmployeeDao(employee);
		
		if(employee2!=null) {
			
			response.put("message", "employee saved succesfully");
			
			response.put("employee",employee2);
			
	        return ResponseEntity.ok(response);
		}
		else {
			
			response.put("message", "employee not saved");
			
			return ResponseEntity.ok(response);
		}
	}
	
	public ResponseEntity<?> saveMultipleEmployeeDtoController(@RequestBody @Valid List<EmployeeDto> employeeDto){
		
		List<Employee> employees  = employeeDto.stream().map(dto ->new Employee(dto.getId(),
				                                                                dto.getName(),dto.getEmail(),dto.getDepartment())).toList();
		
		dao.saveAllEmployeeDao(employees);
		
		return ResponseEntity.ok("employee Saved succesfully");
	}
	
	

}
