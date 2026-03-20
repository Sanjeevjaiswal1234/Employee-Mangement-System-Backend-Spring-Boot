package com.tcs.spring_boot_crud_operation_with_mysql.controller;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.spring_boot_crud_operation_with_mysql.Repository.EmployeeRepository;
import com.tcs.spring_boot_crud_operation_with_mysql.dao.EmployeeDao;
import com.tcs.spring_boot_crud_operation_with_mysql.dto.EmployeeDto;
import com.tcs.spring_boot_crud_operation_with_mysql.entity.Employee;
import com.tcs.spring_boot_crud_operation_with_mysql.exception.EmployeeNotFoundException;

import java.util.*;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private EmployeeRepository employeeRepository;
/*
	@GetMapping("/date")
	public String getDate() {
		return " today Date and Time : "+LocalDateTime.now();
	}
	

	
	@PostMapping(value = "/add/{a}/{b}")
	public int add (@PathVariable(name="a") Integer a, @PathVariable(name="b")Integer b) {
		
		
		return a+b;
	}
	*/
	
	@PostMapping(value = "/saveEmployeeDto")
	public ResponseEntity<?> saveEmployeeDtoController(@RequestBody @Valid EmployeeDto employeeDto) {
		
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setName(employeeDto.getName());
		employee.setEmail(employeeDto.getEmail());
		employee.setDepartment(employeeDto.getDepartment());
		
		HashMap<String , Object> response = new  HashMap<String ,Object>();
		Employee employee2 = employeeDao.SaveEmployeeDao(employee);
		
		if(employee2!=null) {
			response.put("message","employee saved succesfully");
			response.put("employee", employee2);
			
			return ResponseEntity.ok(response);
		}else {
			response.put("message", "employee not saved");
			return ResponseEntity.ok(response);
		}	
			
	}
	/*
	@PostMapping(value = "/saveMultipleEmployeeDto")
	public List<EmployeeDto> saveMultipleDtoController ( @Valid @RequestBody List<EmployeeDto> employeeDto){
		
		employeeDtos.addAll(employeeDto);
		System.out.println(employeeDto);
		
		return employeeDto;
	
	}
	*/
	// with dataBase Multiple employee save
	
	@PostMapping(value = "/saveMultipleEmployeeDto")
	public ResponseEntity<?> saveMultipleEmployeeDtoController(@Valid @RequestBody List<EmployeeDto> employeeDto){
		
		List<Employee> employees = employeeDto.stream()
			    .map(dto -> new Employee(
			            dto.getId(),
			            dto.getName(),
			            dto.getEmail(),
			            dto.getDepartment()
			    ))
			    .toList();

		employeeDao.saveAllEmployeeDao(employees);
		  return ResponseEntity.ok("employees saved successfully");
      
	}
	
//	@GetMapping(value = "/getEmployeeById/{id}")
//	public ResponseEntity<?> getEmployeeByIdController(@PathVariable(name="id") Integer id){
//		
//		if( employeeDtos.isEmpty()) {
//			System.out.println("employeesDtos list is Empty");
//			return ResponseEntity.ok("employees List is Empty");
//		}
//		
	  
//
//		for (EmployeeDto employeeDto : employeeDtos) {
//			if(id==employeeDto.getId()) {
//				return ResponseEntity.ok(employeeDto);
//			}
//		}
//		
//		System.out.println("given id is not found");
//		
//		return ResponseEntity.ok("given id is not found");
//	}
	
	@GetMapping(value = "/allEmployee")
	public List<Employee> getAllEmployeeController(){
		
		return employeeDao.getAllEmployeeDao();
	}
	@GetMapping(value = "/getEmployeeById/{id}")
	public  ResponseEntity<?>   getEmployeeByIdController(@PathVariable(name = "id") Integer id) {
		
		Employee employee=employeeDao.getEmployeeByIdDao(id);
		
		if(employee!=null) {
			
			return ResponseEntity.ok(employee);
			
		}else {
			
			throw new EmployeeNotFoundException("employee not found with id= "+id);
		}
	}
	@PutMapping(value = "/updateEmployeeById/{id}")
	public Employee updateEmployeeController(@PathVariable Long id ,@RequestBody Employee emp) {
		
		Employee existing = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
		
		    existing.setName(emp.getName());
		    existing.setEmail(emp.getEmail());
		    existing.setDepartment(emp.getDepartment());
		    

		  

		    return employeeRepository.save(existing);
		
	}
	
	
	@GetMapping(value = "/getEmployeeByName/{name}")
	public ResponseEntity<?> getEmployeeByNameDao(@PathVariable(name="name") String name) {

		List<Employee> employees=employeeDao.getEmployeeByNameDao(name);
		
		if(employees!=null && employees.size()>0) {
            return ResponseEntity.ok(employees);
		} else {
			throw new EmployeeNotFoundException("employee not found with name= " + name);
		}
	}
	
	@DeleteMapping(value = "/deleteEmployeeById/{id}")
	public ResponseEntity<?> deleteEmployeeByIdDao(@PathVariable(name = "id") int id) {

		boolean isDeleted = employeeDao.deleteEmployeeByIdDao(id);

		if (isDeleted) {
			return ResponseEntity.ok("Employee deleted successfully with id= " + id);
		} else {
			throw new EmployeeNotFoundException("employee not found with id= " + id);
		}
	}
	
	@DeleteMapping(value = "/deleteEmployeeByName/{name}")
	public  ResponseEntity<?> deleteEmployeeByNameDao(@PathVariable(name="name") String name) {

		boolean isDeleted = employeeDao.deleteEmployeeByNameDao(name);

		if (isDeleted) {
			return ResponseEntity.ok("employee deleted successfully with name= " + name);
		} else {
			throw new EmployeeNotFoundException("employee not found with name= " + name);
		}
	}
	
	@GetMapping(value = "/getAllEmployee")
	public ResponseEntity<?> getAllEmployeeDao() {
		List<Employee> employees=employeeDao.getAllEmployeeDao();
		
		if(employees!=null && employees.size()>0) {
            return ResponseEntity.ok(employees);
		} else {
			return ResponseEntity.ok("employee not found in database table is empty");
		}
	}
	
	
	@GetMapping(value = "/getAllEmployeeByPageNumber/{pageNumber}")
   public ResponseEntity<?> getAllEmployeeByPageNumberDao(@PathVariable(name="pageNumber") int pageNumber){
	   
	   Page<Employee> page = employeeDao.getAllEmployeeByPageNumberDao(pageNumber);
	   
	   List<Employee> employees = page.getContent();
	   
	   if(employees == null || employees.size() == 0) {
		   
		 return ResponseEntity.ok("There is no employee data found in database for page number= "+pageNumber+" and total pages="+page.getTotalPages());
	   }
	  
	   return ResponseEntity.ok(page);
   }
   
   @GetMapping(value = "/getAllEmployeeByPageNumberAndByName/{pageNumber}/{name}")
   public Page<Employee> getAllEmployeeByPageNumberAndByNameController(@PathVariable(name="pageNumber") int pageNumber ,@PathVariable(name="name") String name){
	   
	   Page<Employee> page = employeeDao.getAllEmployeeByPageNumberAndByNameDao(pageNumber,name);
	   
	   return page;
   }
   
   @GetMapping(value = "/sortEmployeeByAttributeInAscendingOrder/{attribute}")
   public List<Employee> sortEmployeeByAttributeInAsscendingOrderDao(@PathVariable String attribute){
	   
	   return employeeDao.sortEmployeeByAttributeInAsscendingOrderDao(attribute);
   }
   
   @GetMapping(value = "/sortEmployeeByAttributeInDescendingOrder/{attribute}")
   public List<Employee> sortEmployeeByAttributeInDesscendingOrderDao(@PathVariable String attribute){
	   
	   return employeeDao.sortEmployeeByAttributeInDescendingOrderDao(attribute);
   }

}
