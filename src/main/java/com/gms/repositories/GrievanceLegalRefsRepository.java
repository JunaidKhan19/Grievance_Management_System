package com.gms.repositories;

import com.gms.entities.GrievanceLegalRefs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrievanceLegalRefsRepository extends JpaRepository<GrievanceLegalRefs, Integer> {

    List<GrievanceLegalRefs> findByGrievance_GrvnNum(String grvnNum);

    List<GrievanceLegalRefs> findByLegalRefs_LegRefsnum(String legRefsnum);

    // ⚠ Assignment via SP only
}
