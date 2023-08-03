package com.megha.employeeManagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megha.employeeManagement.entity.Employee;
import com.megha.employeeManagement.repositories.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeRepo.findAll();
	}
	
//	@GetMapping("/employee/{id}")
//    public Employee getEmployeeById(@PathVariable int id) throws Exception {
//        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
//
//        if (optionalEmployee.isPresent()) {
//            return optionalEmployee.get();
//        } else {
//            throw new Exception("Employee with id " + id + " not found");
//        }
//    }
//	
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
	    Optional<Employee> optionalEmployee = employeeRepo.findById(id);

	    if (optionalEmployee.isPresent()) {
	        Employee employee = optionalEmployee.get();
	        return ResponseEntity.ok(employee);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(new Employee(0, "Employee Not found", 0, 0, ""));
	    }
	}
	
	@PostMapping("/employee/insert")
	public String insertEmployee(@RequestBody Employee e)
	{
		if(employeeRepo.existsById(e.getId()))
			return "Employee already exists";
		else
		{
			employeeRepo.save(e);
			return "Successfully inserted employee with id "+e.getId();
		}
	}
	
	@DeleteMapping("/employee/delete/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		
		if(employeeRepo.existsById(id))
		{
			employeeRepo.deleteById(id);
			return "Successfully deleted";
		}
		else
		{
			return "No match for employee with id "+id;
		}
	}
	
	@PutMapping("/employee/update")
	public String updateEmployee(@RequestBody Employee e) {
		if(employeeRepo.existsById(e.getId()))
		{
			employeeRepo.save(e);
			return "Employee updated";
		}
		else
		{
			return "No employee found with the id";
		}
			
	}
	
	@GetMapping("/employee/designation/{designation}")
	public List<Employee> findEmployeeByDesignation(@PathVariable String designation)
	{
		return employeeRepo.findByDesignation(designation);
	}
	
	@GetMapping("/employee/sortBySalary/{designation}")
	public List<Employee> sortBysalary(@PathVariable String designation)
	{
		return employeeRepo.sortBySalary(designation);
	}	
}
