package com.gms.repositories;

import com.gms.entities.Grievance;
import com.gms.enums.GrievanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Integer> {

    Grievance findByGrvnNum(String grvnNum);

    boolean existsByGrvnNum(String grvnNum);

    @Query(value = "SELECT * FROM vw_grievances_basic " +
            "WHERE (:empNum IS NULL OR empnum = :empNum) " +
            "AND (:status IS NULL OR status = :status)", nativeQuery = true)
    List<Object[]> findAllBasicByEmpNumAndStatus(@Param("empNum") String empNum,
                                                 @Param("status") String status);


    // Grievances count by status
    @Query(value = "SELECT * FROM vw_grievances_by_status", nativeQuery = true)
    List<Object[]> countByStatus();

    // Grievances count by category
    @Query(value = "SELECT * FROM vw_grievances_by_category", nativeQuery = true)
    List<Object[]> countByCategory();

    // Grievances list by category, optionally filtered
    @Query(value = "SELECT * FROM vw_grievances_list_by_category " +
            "WHERE (:category IS NULL OR ctgnum = :category)", nativeQuery = true)
    List<Object[]> listByCategory(@Param("category") String category);

    // Single grievance details with employee info
    @Query(value = "SELECT * FROM vw_grievance_with_employee WHERE grvnnum = ?1", nativeQuery = true)
    Object[] findWithEmployee(String grvnNum);

    // Resolved grievances with resolution
    @Query(value = "SELECT * FROM vw_resolved_grievances", nativeQuery = true)
    List<Object[]> resolvedGrievances();
}
