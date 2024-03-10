package com.gl.EmployeePro.Model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;




@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo repo1;
	
	public void addUpdate(Employee employee) {
		repo1.save(employee);
	}
	public void delete(Employee employee) {
		repo1.delete(employee);
	}
	public List<Employee> getall(){
		return repo1.findAll();
	}
	public Employee FindById(int id) {
		if(repo1.findById(id).isEmpty()) {
			return null;
		}else {
			return repo1.findById(id).get();
		}
	}
	public List<Employee> getBySortOnly(String Columns, Direction direction ){
		return repo1.findAll(Sort.by(direction, Columns));
	}
	
		public List<Employee> filter (String searchKey){
			Employee dummy = new Employee();
				dummy.setFirstName(searchKey);
			ExampleMatcher exampleMatcher=ExampleMatcher.matching().withMatcher("firstName",ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id","lastName","email");
			Example<Employee> example =Example.of(dummy,exampleMatcher);
			return repo1.findAll(example);
		}
	
}