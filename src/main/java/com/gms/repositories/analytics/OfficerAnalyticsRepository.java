package com.gms.repositories.analytics;

import com.gms.dto.view.OfficerBasicView;
import com.gms.dto.view.OfficerWorkloadView;
import com.gms.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficerAnalyticsRepository extends JpaRepository<Officer, Integer> {

    // Fetch all officers basic info
    @Query(value = "SELECT * FROM vw_officers_basic", nativeQuery = true)
    List<OfficerBasicView> findAllOfficersBasic();

    // Officers workload
    @Query(value = "SELECT * FROM vw_officer_workload", nativeQuery = true)
    List<OfficerWorkloadView> getOfficerWorkload();

    // Officers by category count
    @Query(value = "SELECT * FROM vw_officers_by_category", nativeQuery = true)
    List<Object[]> countByCategory();

    // Officers with category details
    @Query(value = "SELECT * FROM vw_officers_with_category", nativeQuery = true)
    List<Object[]> withCategory();
}
