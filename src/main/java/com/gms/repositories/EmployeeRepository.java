package com.gms.repositories;

import com.gms.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByUserNum(String userNum);

    // Fetch by userNum (used in dashboard, SP fetches preferred for updates)
    Employee findByUserNum(String userNum);

    // Optional: by email
    Employee findByEmail(String email);

    // Fetch all employees basic info via view (read-only)
    @Query(value = "SELECT * FROM vw_employees_basic", nativeQuery = true)
    List<Employee> findAllBasic();

    // Employees count by department
    @Query(value = "SELECT * FROM vw_employees_by_department", nativeQuery = true)
    List<Object[]> countByDepartment();

    // Employees count by role
    @Query(value = "SELECT * FROM vw_employees_by_role", nativeQuery = true)
    List<Object[]> countByRole();

    // ⚠ Note: For create/update/delete, use SPs only!
}
