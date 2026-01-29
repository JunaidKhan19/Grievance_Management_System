package com.gms.repositories;

import com.gms.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Integer> {

    boolean existsByUserNum(String userNum);

    Officer findByUserNum(String officerNum);

    List<Officer> findByCategory_CtgNum(String ctgNum);

    // Fetch all officers basic info
    @Query(value = "SELECT * FROM vw_officers_basic", nativeQuery = true)
    List<Officer> findAllBasic();

    // Officers by category count
    @Query(value = "SELECT * FROM vw_officers_by_category", nativeQuery = true)
    List<Object[]> countByCategory();

    // Officers workload
    @Query(value = "SELECT * FROM vw_officer_workload", nativeQuery = true)
    List<Object[]> workload();

    // Officers with category details
    @Query(value = "SELECT * FROM vw_officers_with_category", nativeQuery = true)
    List<Object[]> withCategory();

    // ⚠ Use SPs for create/update/delete
}
