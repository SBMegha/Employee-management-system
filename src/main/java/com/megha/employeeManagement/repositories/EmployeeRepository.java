package com.megha.employeeManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.megha.employeeManagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	public List<Employee> findByDesignation(String designation);
	
	@Query("from Employee where designation=:designation order by salary")
	public List<Employee> sortBySalary(String designation);
}
