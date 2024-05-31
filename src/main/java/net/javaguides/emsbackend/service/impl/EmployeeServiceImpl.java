package net.javaguides.emsbackend.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.emsbackend.Entity.Employee;
import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.exception.ResourceNotFoundException;
import net.javaguides.emsbackend.mapper.EmployeeMapper;
import net.javaguides.emsbackend.repository.EmployeeRepository;
import net.javaguides.emsbackend.service.EmployeeService;


import org.springframework.context.annotation.Configuration;

@Configuration
@CrossOrigin("*")
@Service
@Getter
@Setter
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
			.orElseThrow(() -> 
			new ResourceNotFoundException("Employee is does not exist with given id: "+ employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmplooyes() {
		List<Employee> employees = null ;
		try {
			employees = employeeRepository.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
									.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is does not exist with given ID : " + employeeId));

		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());

		Employee updatedEmployeeObj = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public EmployeeDto deleteEmployee(Long employeeId, EmployeeDto deleteEmployee) {
		// find the employee by id
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is does not exist with given ID : " + employeeId));
	    // Save the updated employee information to the repository
	    employeeRepository.deleteById(employeeId);
	    // Map the updated employee to DTO and return
	    return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public Void DeleteEmployee(Long employeeId) {
		employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is does not exist with given ID : " + employeeId));
		 employeeRepository.deleteById(employeeId);
		 return null;
		 }
	
}



