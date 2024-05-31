package net.javaguides.emsbackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import net.javaguides.emsbackend.dto.EmployeeDto;

@CrossOrigin("*")
public interface EmployeeService {
	//Create Api
	EmployeeDto createEmployee (EmployeeDto employeeDto);
	
	//Get employee by id API
	EmployeeDto getEmployeeById(Long employeeId);
	
	//Get all employee by API
	List<EmployeeDto> getAllEmplooyes();
	 
	//Update employee API
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee);
	
	//Delete API
	EmployeeDto deleteEmployee(Long employeeId, EmployeeDto deleteEmployee);
	
	Void DeleteEmployee(Long employeeId);
}
