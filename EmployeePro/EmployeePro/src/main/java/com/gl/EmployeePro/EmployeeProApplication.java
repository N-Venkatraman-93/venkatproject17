package com.gl.EmployeePro;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.EmployeePro.Model.AppService;
import com.gl.EmployeePro.Model.AppUser;

@SpringBootApplication
public class EmployeeProApplication implements CommandLineRunner {
	@Autowired
	AppService appservice;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeProApplication.class, args);
	}	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Set<String> auadmin=new HashSet<>();
		auadmin.add("admin");
		
		Set<String> auuser=new HashSet<>();
		auuser.add("user");
		
		PasswordEncoder e1= new BCryptPasswordEncoder();
		
		AppUser appAdmin= new AppUser(1,"admin","admin",e1.encode("admin123"),auadmin);
		appservice.add(appAdmin);
		AppUser appUser= new AppUser(2,"user","user",e1.encode("user123"),auuser);
		appservice.add(appUser);
		
	}
}
