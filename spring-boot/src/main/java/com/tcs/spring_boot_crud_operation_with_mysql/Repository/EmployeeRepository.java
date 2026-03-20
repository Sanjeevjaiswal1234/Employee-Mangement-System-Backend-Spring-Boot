package com.tcs.spring_boot_crud_operation_with_mysql.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tcs.spring_boot_crud_operation_with_mysql.entity.Employee;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	
	//select * from employee where name=name;
		List<Employee> findByName(String name);
		
		Optional<Employee> findByEmail(String email);
		
		Optional<Employee> findByDepartment(String department);
		
	    @Query(value = "Delete from Employee e where e.name=?1")
		@Modifying
		@Transactional
		void deleteEmployeeByName(String name);
	    
	    Page<Employee> findByName(String name,Pageable pageable);
	    
	  List<Employee> findAll();

	  Optional<Employee> findById(Long id);
	    


}
