package com.gl.EmployeePro;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.EmployeePro.Model.AppService;
import com.gl.EmployeePro.Model.Employee;
import com.gl.EmployeePro.Model.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@Autowired
	AppService appservice;
	
	@PostMapping("api/add")
	public String addapi(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole ="admin";
		boolean roleFound =false;
		
		System.out.println("current login by: "+ authentication.getName());
		
		Collection<? extends GrantedAuthority> grantedroles=
				auth.getContext().getAuthentication().getAuthorities();// get the role(s) mapped with the user
		
		for(int i=0; i< grantedroles.size(); i++) {
			String role =grantedroles.toArray()[i].toString();
			System.out.println("my role : "+role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			Employee p1=new Employee(0,firstName,lastName,email);
			service.addUpdate(p1);
			return "added";
		}else {
			return "Access Denid";	
		}
	}
		
	@PutMapping("api/update")
	public String updateapi(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole ="admin";
		boolean roleFound =false;
		
		System.out.println("current login by: "+ authentication.getName());
		
		Collection<? extends GrantedAuthority> grantedroles=
				auth.getContext().getAuthentication().getAuthorities();// get the role(s) mapped with the user
		
		for(int i=0; i< grantedroles.size(); i++) {
			String role =grantedroles.toArray()[i].toString();
			System.out.println("my role : "+role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			Employee p1=new Employee(id,firstName,lastName,email);
			service.addUpdate(p1);
			return "Updated";
		}
		else {
			return "Access Denid";	
		}
	}
		
	@GetMapping("api/getByAll")
	public List <Employee> getAll(){
		return service.getall();
	}
	@DeleteMapping("api/delete")
	public String Deleteapi (@RequestParam int id,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole ="admin";
		boolean roleFound =false;
		
		System.out.println("current login by: "+ authentication.getName());
		
		Collection<? extends GrantedAuthority> grantedroles=
				auth.getContext().getAuthentication().getAuthorities();// get the role(s) mapped with the user
		
		for(int i=0; i< grantedroles.size(); i++) {
			String role =grantedroles.toArray()[i].toString();
			System.out.println("my role : "+role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			Employee p1=new Employee(id,"","","");
			service.delete(p1);
			return "Deleted employee id - "+id;
		}
		else {
			return "Access Denid";	
		}
	
	}
	@GetMapping("api/searchfirstName")
	public List<Employee> searchfirstname(@RequestParam String firstName){
		return service.filter(firstName);
	}
	
	@GetMapping("api/search")
	public List<Employee> searchapi( @RequestParam String firstName) {
		return service.getBySortOnly("firstName",Direction.ASC);
		
	}
	
	@GetMapping("api/getById")
	public Employee getById(@RequestParam int  id) {
		return service.FindById(id);
	}
	

}
