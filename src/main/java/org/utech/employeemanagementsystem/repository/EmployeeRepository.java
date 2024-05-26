package org.utech.employeemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.utech.employeemanagementsystem.model.Employee;

import java.util.List;

@Service
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContaining(String searchChar);

    List<Employee> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT MAX(e.salary) FROM Employee e")
    Integer findHighestSalary();

    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC LIMIT 10")
    List<Employee> findTop10HighestEarningEmployees();
}
