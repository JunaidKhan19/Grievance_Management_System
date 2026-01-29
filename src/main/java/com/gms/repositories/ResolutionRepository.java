package com.gms.repositories;

import com.gms.entities.Resolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Integer> {

    List<Resolution> findByGrievance_GrvnNum(String grvnNum);

    // ⚠ SP-driven: resolve_grievance
}
