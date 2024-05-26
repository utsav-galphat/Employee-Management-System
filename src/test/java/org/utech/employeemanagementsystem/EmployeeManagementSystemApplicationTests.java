package org.utech.employeemanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.utech.employeemanagementsystem.service.EmployeeServiceImplTest;

@SpringBootTest
class EmployeeManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void runEmployeeServiceImplTest() {
        EmployeeServiceImplTest employeeServiceImplTest = new EmployeeServiceImplTest();
        employeeServiceImplTest.setUp();
        employeeServiceImplTest.testGetAllEmployees();
        employeeServiceImplTest.testGetEmployeesByNameSearch();
        employeeServiceImplTest.testGetEmployeeById();
        employeeServiceImplTest.testGetEmployeeByIdWhichIsNotPresent();
        employeeServiceImplTest.testCreateEmployee();
        employeeServiceImplTest.testUpdateEmployee();
        employeeServiceImplTest.testDeleteEmployee();
        employeeServiceImplTest.testGetHighestSalaryOfEmployees();
        employeeServiceImplTest.testGetTop10HighestEarningEmployeeNames();
    }
}
