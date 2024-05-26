package org.utech.employeemanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utech.employeemanagementsystem.dto.EmployeeRequestDto;
import org.utech.employeemanagementsystem.model.Employee;
import org.utech.employeemanagementsystem.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all employees"),
            @ApiResponse(responseCode = "204", description = "No employees found")
    })
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @Operation(summary = "Get an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Operation(summary = "Search employees by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found employees matching the name"),
            @ApiResponse(responseCode = "204", description = "No employees found matching the name")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> getEmployeesByName(@Param("name") String name) {
        return new ResponseEntity<>(employeeService.getEmployeesByNameSearch(name), HttpStatus.OK);
    }

    @Operation(summary = "Get the highest salary of employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the highest salary"),
            @ApiResponse(responseCode = "204", description = "No employees found")
    })
    @GetMapping("/highest-salary")
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        Integer highestSalary = employeeService.getHighestSalaryOfEmployees();
        if (highestSalary == null) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(highestSalary, HttpStatus.OK);
    }

    @Operation(summary = "Get top 10 highest earning employee names")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the top 10 highest earning employees"),
            @ApiResponse(responseCode = "204", description = "No employees found")
    })
    @GetMapping("/top-10-highest-earning")
    public ResponseEntity<List<Employee>> getTop10HighestEarningEmployeeNames() {
        List<Employee> topEmployees = employeeService.getTop10HighestEarningEmployeeNames();
        if (topEmployees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(topEmployees, HttpStatus.OK);
    }

    @Operation(summary = "Create a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequestDto employee) {
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeRequestDto employee, @PathVariable Long id) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
