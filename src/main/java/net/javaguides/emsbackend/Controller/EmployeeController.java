package net.javaguides.emsbackend.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.service.EmployeeService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")//http://localhost:8080/api/employees

@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    //@CrossOrigin("origins = http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }
    
    //Build get employee API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeId){
    	EmployeeDto employeeDto = employeeService.getEmployeeById(employeId);
    	return ResponseEntity.ok(employeeDto);
    }
    
    //Build Get All REST APIS
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
    	List<EmployeeDto> employees = employeeService.getAllEmplooyes();
    	return ResponseEntity.ok(employees);
    }
    
    //BUILD UPDATE EMPLOYEE REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployee){
    	EmployeeDto employeeDto = employeeService.updateEmployee( employeeId, updateEmployee);
    	return ResponseEntity.ok(employeeDto);
    }
    
    //New delete employee API
    @DeleteMapping("{id}")
    public ResponseEntity<String> DeleteEmployee (@PathVariable("id")Long employeeId){
    	employeeService.DeleteEmployee(employeeId);
    	return ResponseEntity.ok("Employee Deleted successful!");
    }
    
}



