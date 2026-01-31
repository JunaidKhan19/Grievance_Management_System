package com.gms.repositories.sp;

import com.gms.entities.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrievanceTimelineSPRepository extends JpaRepository<Grievance, Integer> {

    @Query(value = "CALL fetch_investigation_appeals(:grvnNum)", nativeQuery = true)
    List<Object[]> fetchInvestigationAppeals(@Param("grvnNum") String grvnNum);

    @Query(value = "CALL fetch_grievance_appeals(:grvnNum)", nativeQuery = true)
    List<Object[]> fetchGrievanceAppeals(@Param("grvnNum") String grvnNum);
}
