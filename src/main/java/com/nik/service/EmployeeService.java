package com.nik.service;

import java.util.List;

import com.nik.modal.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();

	public void createEmployee(Employee emp);

	public void createAllEmployee(List<Employee> empList);

	public void updateEmployee(Employee emp); 

	public void deleteEmployee(Employee emp);

	public void deleteEmployeeById(int id);

	public Employee findEmployeeById(long id);

	public void deleteAllEmployees();

}