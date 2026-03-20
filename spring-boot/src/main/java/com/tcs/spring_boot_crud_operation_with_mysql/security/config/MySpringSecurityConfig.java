//package com.tcs.spring_boot_crud_operation_with_mysql.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.tcs.spring_boot_crud_operation_with_mysql.Repository.EmployeeRepository;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//
//public class MySpringSecurityConfig {
//	
//	private final EmployeeRepository employeeRepository;
//	
//	
//	MySpringSecurityConfig(EmployeeRepository employeeRepository){
//		
//		this.employeeRepository = employeeRepository;
//	}
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//	    httpSecurity
//	        .csrf(a -> a.disable())
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/saveEmployeeDto").permitAll()   // 🔥 ADD THIS
//	            .requestMatchers("/employee/**").hasRole("USER")
//	            .requestMatchers("/user/**").hasRole("ADMIN")
//	            .requestMatchers("/auth/employee/**").permitAll()
//	            .anyRequest().authenticated()
//	        )
//	        .httpBasic(Customizer.withDefaults());
//
//	    return httpSecurity.build();
//	}
//
//}
 package com.tcs.spring_boot_crud_operation_with_mysql.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())   // ✅ CSRF disable (important for React)
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()   // ✅ Allow all requests
            );

        return http.build();
    }
}
