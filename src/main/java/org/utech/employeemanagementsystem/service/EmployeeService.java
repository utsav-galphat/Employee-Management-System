package org.utech.employeemanagementsystem.service;

import org.utech.employeemanagementsystem.dto.EmployeeRequestDto;
import org.utech.employeemanagementsystem.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByNameSearch(String name);

    Employee getEmployeeById(Long id);

    Integer getHighestSalaryOfEmployees();

    Employee createEmployee(EmployeeRequestDto employee);

    Employee updateEmployee(EmployeeRequestDto employee, Long Id);

    void deleteEmployee(Long id);

    List<Employee> getTop10HighestEarningEmployeeNames();
}
