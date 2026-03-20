package com.tcs.spring_boot_crud_operation_with_mysql.mapping.one_to_one;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GenerationType;


@Entity
public class Aadhar {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true,nullable = false)
	private String aadharNumber;
	
	@OneToOne(mappedBy = "adhar")
	private Person person;
	
	public void setId(Long id) {
		this.id = id;
	}
    public String getAadharNumber() {
    	
    	   return aadharNumber;
    }
    public void setAadhaarNumber(String aadhaarNumber) {
		this.aadharNumber = aadhaarNumber;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	

}
