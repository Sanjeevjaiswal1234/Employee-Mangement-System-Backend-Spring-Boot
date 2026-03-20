package com.tcs.spring_boot_crud_operation_with_mysql.mapping.one_to_one;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person ,Long> {

}
