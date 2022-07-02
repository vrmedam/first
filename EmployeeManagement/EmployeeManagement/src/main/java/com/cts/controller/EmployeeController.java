package com.cts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Employee;
import com.cts.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeesById(@PathVariable Integer id)
	{
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent())
		return employee.get();
		else
			return null;
	}
	
	
	@DeleteMapping("/employees/{id}")
	public Boolean deletetEmployeesById(@PathVariable Integer id)
	{
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent())
		{ 
			employeeRepository.deleteById(id);
			return true;
		}
		else
			return false;
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		
		return employeeRepository.save(employee);	
		
	}
	
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee employee,@PathVariable Integer id)
	{
		Optional<Employee> employeetemp=employeeRepository.findById(id);
		if(employeetemp.isPresent())
		{ 
			
			Employee employeeToUpdate=employeetemp.get();
			employeeToUpdate.setDesignation(employee.getDesignation());
			employeeToUpdate.setName(employee.getName());
			employeeToUpdate.setSal(employee.getSal());
			
			
			employeeToUpdate=employeeRepository.save(employeeToUpdate);
			return employeeToUpdate;
		}
		else
			return null;
		
		
	}

}
