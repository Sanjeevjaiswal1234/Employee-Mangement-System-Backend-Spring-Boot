package com.tcs.spring_boot_crud_operation_with_mysql.mapping.one_to_one;

import org.springframework.stereotype.Repository;

@Repository
public class PersonAdharDao {
	
	
	private final PersonRepository personRepository ;
	
	
	public PersonAdharDao(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	
	public Person savePersonWithAdhar(Person person) {
		return personRepository.save(person);
	}


}
