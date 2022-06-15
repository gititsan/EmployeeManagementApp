package com.nik.repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nik.modal.Employee;

@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE lower(e.name) like lower(concat('%', :searchTerm,'%'))")
	public List<Employee> findByPlaceContaining(@Param("searchTerm") String name);

	
	@Profile("test")
	@Bean
	public static String testDatabaseConnection() {
		System.out.println("Cnx to DB specific to Test Environement");;
		return "Cnx to DB specific to Test Environement";
	}
	
	
	@Profile("dev")
	@Bean
	public static String prodDatabaseConnection() {
		System.out.println("Cnx to DB specific to PROD Environement");;
		return "Cnx to DB specific to PROD Environement";
	}
}