package com.nik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.nik.modal.Employee;
import com.nik.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {


	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void createAllEmployee(List<Employee> empList) {
		employeeRepository.saveAll(empList);
	}

	@Override
	public void createEmployee(Employee emp) {
		employeeRepository.save(emp);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findEmployeeById(long id) {
		return employeeRepository.getOne(id);
	}

	@SuppressWarnings("removal")
	@Override
	public void deleteEmployeeById(int id) {
		employeeRepository.deleteById(new Long(id));
	}

	@Override
	public void updateEmployee(Employee emp) {
		employeeRepository.save(emp);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		employeeRepository.delete(emp);
	}

	@Override
	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}

	
	
	


}