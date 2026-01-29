package com.gms.repositories;

import com.gms.entities.Investigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestigationRepository extends JpaRepository<Investigation, Integer> {

    List<Investigation> findByGrievance_GrvnNum(String grvnNum);

    List<Investigation> findByOfficer_UserNum(String officerNum);

    // ⚠ SP-driven: assign_grievance
}
