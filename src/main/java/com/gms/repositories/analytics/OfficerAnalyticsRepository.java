package com.gms.repositories.analytics;

import com.gms.dto.view.OfficerBasicView;
import com.gms.dto.view.OfficerWorkloadView;
import com.gms.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficerAnalyticsRepository extends JpaRepository<Officer, Integer> {

    @Query(value = "SELECT * FROM vw_officers_basic", nativeQuery = true)
    List<OfficerBasicView> findAllOfficersBasic();

    @Query(value = "SELECT * FROM vw_officer_workload", nativeQuery = true)
    List<OfficerWorkloadView> getOfficerWorkload();
}
