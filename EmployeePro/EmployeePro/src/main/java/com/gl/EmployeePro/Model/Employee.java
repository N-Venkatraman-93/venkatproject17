package com.gl.EmployeePro.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
		
		@Id
		@GeneratedValue (strategy = GenerationType.AUTO)
		private int id;
		private String firstName;
		private String lastName;
		private String email;
}
