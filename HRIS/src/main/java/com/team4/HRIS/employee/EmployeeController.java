package com.team4.HRIS.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "api/v1/employee")
public class EmployeeController {
	
    private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	    
	// Reads departments from a specific department
    @GetMapping(value = "/department/{depId}")
    public List<Employee> getEmployees(@PathVariable int depId){
        return EmployeeService.getAll(depId);
    }

    // You can recieve multiple values by @RequestMapping(value = /{val1}/{val2}
    // Gets an employee based on their unique employee Id
    @GetMapping(value = "/id/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
}
    //TODO: Read request from employee
    //TODO: Read paystub

    // Creates a new employee in the database
    @PostMapping(path = "/new")
    public void createEmployee(@RequestBody Employee employee){
	    employeeService.createEmployee(employee);
    }
    //TODO: Create a schedule
    //TODO: Create a request

    // Deletes an employee from the database
    @DeleteMapping(path = "/delete/{empId}")
    public void deleteEmployee(@PathVariable int empId){
	    employeeService.deleteEmployee(empId);
    }
    // Updates an employee in the database
    @PutMapping(path = "/update")
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
    }
    //TODO: Update address
    //TODO: Update payroll
    //TODO: Update Schedule
    //TODO: Update employee request
    //TODO: Update benefits

    }

