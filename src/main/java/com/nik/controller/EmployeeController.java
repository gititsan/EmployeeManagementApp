package com.nik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nik.modal.Employee;
import com.nik.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping({ "/employees" })
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	private static List<Employee> employees = createList();

	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	/*
	@GetMapping(produces = "application/json")
	public List<Employee> firstPage() {
		System.out.println("get employees call");
		return employeeService.getAllEmployees();
	}*/
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<Object>  firstPage() {
		System.out.println("get employees call");
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	
	
	
/*
	@DeleteMapping(path = { "/{id}" })
	public Employee delete(@PathVariable("id") Integer id) {
		System.out.println("delete employees call");
		employeeService.deleteEmployeeById(id);
		return null;
	} */
	
	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<Object>  delete(@PathVariable("id") Integer id) {
		System.out.println("delete employees call");
		Employee e= employeeService.findEmployeeById(id);
		if(e.equals(null))
		{
			return new ResponseEntity<>("Employee doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);	

		}else {
			employeeService.deleteEmployeeById(id);
			return new ResponseEntity<>("Employee is deleted successfully", HttpStatus.OK);	
		}

	}

	/*@GetMapping(path = { "/{id}" })
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return employeeService.findEmployeeById(id);
	}*/
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK); 
	}

	
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Employee emp) {
		System.out.println("create employees call");

		employeeService.createEmployee(emp);
		return new ResponseEntity<>(emp, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp) {
		System.out.println("create employees call");

		employeeService.createEmployee(emp);
		return new ResponseEntity<>(emp, HttpStatus.OK); 
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateEmployeeByID(@PathVariable("id") long id, @RequestBody Employee emp) {
		 //emp= employeeService.findEmployeeById(id);
		System.out.println(emp);
		if(emp.equals(null))
		{
			return new ResponseEntity<>("Employee doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);

		}else {
			employeeService.updateEmployee(emp);
			return new ResponseEntity<>(emp, HttpStatus.OK); 	
		}
	}

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody Employee emp) {
		System.out.println("update employees call");
		if(emp.equals(null))
		{
			return new ResponseEntity<>("Employee doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);	

		}else {
			employeeService.updateEmployee(emp);
			return new ResponseEntity<>(emp, HttpStatus.OK); 
		}

	}
	
 

	private static List<Employee> createList() {
		List<Employee> tempEmployees = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setName("emp1");
		emp1.setDesignation("manager");
		emp1.setEmpId(1l);
		emp1.setSalary(3000);

		Employee emp2 = new Employee();
		emp2.setName("emp2");
		emp2.setDesignation("developer");
		emp2.setEmpId(2l);
		emp2.setSalary(3000);
		tempEmployees.add(emp1);
		tempEmployees.add(emp2);
		return tempEmployees;
	}

}
