package org.utech.employeemanagementsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.utech.employeemanagementsystem.dto.EmployeeRequestDto;
import org.utech.employeemanagementsystem.exception.EmployeeNotFoundException;
import org.utech.employeemanagementsystem.model.Employee;
import org.utech.employeemanagementsystem.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new Employee(1L, "John", 50000, 30, "");
        Employee employee2 = new Employee(2L, "Alice", 60000, 35, "");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(employees, result);
    }

    @Test
    public void testGetEmployeesByNameSearch() {
        String name = "John";

        Employee employee1 = new Employee(1L, "John Doe", 50000, 30, "");
        Employee employee2 = new Employee(2L, "John Smith", 60000, 35, "");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findByNameContainingIgnoreCase(name)).thenReturn(employees);
        List<Employee> result = employeeService.getEmployeesByNameSearch(name);

        assertEquals(employees, result);
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = new Employee(1L, "John Doe", 50000, 30, "");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmployeeById(1L);

        assertEquals(employee, result);
    }

    @Test
    public void testGetEmployeeByIdWhichIsNotPresent() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(1L);
        });
    }

    @Test
    public void testCreateEmployee() {
        EmployeeRequestDto employee = new EmployeeRequestDto("John Doe", 50000, 30, "");

        when(employeeRepository.save(any(Employee.class))).thenAnswer(invocation -> {
            Employee savedEmployee = invocation.getArgument(0);
            savedEmployee.setId(1L);
            return savedEmployee;
        });

        Employee result = employeeService.createEmployee(employee);
        System.out.println(result);
        assertNotNull(result.getId());

    }

    @Test
    public void testUpdateEmployee() {
        Employee existingEmployee = new Employee(1L, "John Doe", 50000, 30, "");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));

        EmployeeRequestDto updatedEmployeeData = new EmployeeRequestDto( "John Smith", 60000, 35, "");

        Employee result = employeeService.updateEmployee(updatedEmployeeData, 1L);

        assertEquals(updatedEmployeeData.getName(), result.getName());
        assertEquals(updatedEmployeeData.getAge(), result.getAge());
        assertEquals(updatedEmployeeData.getSalary(), result.getSalary());
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee(1L, "John Doe", 50000, 30, "");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    public void testGetHighestSalaryOfEmployees() {
        when(employeeRepository.findHighestSalary()).thenReturn(80000);

        Integer result = employeeService.getHighestSalaryOfEmployees();

        assertEquals(80000, result);
    }

    @Test
    public void testGetTop10HighestEarningEmployeeNames() {
        Employee employee1 = new Employee(1L, "John Doe", 50000, 30, "");
        Employee employee2 = new Employee(2L, "John Smith", 60000, 35, "");
        List<Employee> topEmployees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findTop10HighestEarningEmployees()).thenReturn(topEmployees);

        List<Employee> result = employeeService.getTop10HighestEarningEmployeeNames();

        assertEquals(topEmployees, result);
    }

}