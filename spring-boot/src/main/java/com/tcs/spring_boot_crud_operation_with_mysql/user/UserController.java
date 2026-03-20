package com.tcs.spring_boot_crud_operation_with_mysql.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = "/user",method=RequestMethod.GET)
	public String getUser() {
		return "This is user controller";
	}
}
