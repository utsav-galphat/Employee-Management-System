package org.utech.employeemanagementsystem.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.utech.employeemanagementsystem.dto.EmployeeRequestDto;
import org.utech.employeemanagementsystem.exception.EmployeeNotFoundException;
import org.utech.employeemanagementsystem.model.Employee;
import org.utech.employeemanagementsystem.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else throw new EmployeeNotFoundException("No Employee Found with id: " + id);
    }

    @Override
    @Transactional
    public Employee createEmployee(EmployeeRequestDto employee) {
        Employee newEmployee = Employee.builder()
                .age(employee.getAge())
                .name(employee.getName())
                .salary(employee.getSalary()).build();
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee updateEmployee(EmployeeRequestDto employee, Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee updatedEmployee = Employee.builder().name(employee.getName())
                    .age(employee.getAge())
                    .salary(employee.getSalary())
                    .profile_image(employee.getProfile_image())
                    .id(id).build();
            employeeRepository.save(updatedEmployee);
            return updatedEmployee;
        }
        else throw new EmployeeNotFoundException("No Employee Found with id: " + id);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
            employeeOptional.get();
        }
        else throw new EmployeeNotFoundException("No Employee Found with id: " + id);
    }

    @Override
    public Integer getHighestSalaryOfEmployees() {
        return employeeRepository.findHighestSalary();
    }

    @Override
    public List<Employee> getTop10HighestEarningEmployeeNames() {
        return employeeRepository.findTop10HighestEarningEmployees();
    }

}
