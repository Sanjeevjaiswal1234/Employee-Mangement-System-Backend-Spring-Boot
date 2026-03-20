package com.tcs.spring_boot_crud_operation_with_mysql.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tcs.spring_boot_crud_operation_with_mysql.Repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import com.tcs.spring_boot_crud_operation_with_mysql.entity.Employee;

@Repository
public class EmployeeDao {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public Employee SaveEmployeeDao(Employee employees) {
		
	return employeeRepository.saveAndFlush(employees);		
		
	}
	public List<Employee> saveAllEmployeeDao(List<Employee> employees) {

		return employeeRepository.saveAllAndFlush(employees);

	}
	public Employee getEmployeeByIdDao(int id) {

		Optional<Employee> optional = employeeRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	public List<Employee> getEmployeeByNameDao(String name) {

		return employeeRepository.findByName(name);
	}
	
	public boolean deleteEmployeeByIdDao(int id) {

		Optional<Employee> optional = employeeRepository.findById(id);

		if (optional.isPresent()) {
			Employee employee = optional.get();
			employeeRepository.delete(employee);
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean deleteEmployeeByNameDao(String name) {
		
		List<Employee> employees = employeeRepository.findByName(name);
		
		if (employees == null || employees.size() == 0) {
			return false;
			
		}

		
		employeeRepository.deleteEmployeeByName(name);
		
		return true;
	}
	
	public List<Employee> getAllEmployeeDao() {
		return employeeRepository.findAll();
	}
	
	
	public Page<Employee> getAllEmployeeByPageNumberDao(int pageNumber) {
		
	return	employeeRepository.findAll(PageRequest.of(pageNumber , 5));
		   		
		
	}
	public Page<Employee> getAllEmployeeByPageNumberAndByNameDao(int pageNumber, String name) {
		
		Pageable pageable = PageRequest.of(pageNumber, 5);
		
		return employeeRepository.findByName(name, pageable);
	}
	public List<Employee> sortEmployeeByAttributeInAsscendingOrderDao(String attribute) {
		
		
		return employeeRepository.findAll(Sort.by(Sort.Direction.ASC , attribute));
	}
	
	public List<Employee> sortEmployeeByAttributeInDescendingOrderDao(String attribute){
		
		return employeeRepository.findAll(Sort.by(Sort.Direction.DESC,attribute));
	}

	
}
