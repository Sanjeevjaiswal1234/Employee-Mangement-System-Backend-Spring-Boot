package com.tcs.spring_boot_crud_operation_with_mysql.mapping.one_to_one;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;



@RequestMapping("/person-adhar")
@RestController
public class PersonAdharController {
	
	 
private final PersonAdharDao personAdharDao;
	
	public PersonAdharController(PersonAdharDao personAdharDao) {
		super();
		this.personAdharDao = personAdharDao;
	}
	
	@PostMapping("/savePersonAdhar")
	public Person savePersonWithAdhar(@RequestBody Person person) {
		return personAdharDao.savePersonWithAdhar(person);
	}

   
}
