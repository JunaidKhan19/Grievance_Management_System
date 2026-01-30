package com.gms.repositories.analytics;

import com.gms.dto.view.EmployeeBasicView;
import com.gms.dto.view.EmployeeByDepartmentView;
import com.gms.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAnalyticsRepository extends JpaRepository<Employee, Integer> {

    // Fetch all employees basic info via view (read-only)
    @Query(value = "SELECT * FROM vw_employees_basic", nativeQuery = true)
    List<EmployeeBasicView> findAllEmployeesBasic();

    // Employees count by department
    @Query(value = "SELECT * FROM vw_employees_by_department", nativeQuery = true)
    List<EmployeeByDepartmentView> countEmployeesByDepartment();
}
