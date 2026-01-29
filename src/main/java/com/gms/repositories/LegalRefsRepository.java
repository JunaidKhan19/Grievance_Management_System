package com.gms.repositories;

import com.gms.entities.LegalRefs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRefsRepository extends JpaRepository<LegalRefs, Integer> {

    LegalRefs findByLegRefsnum(String legRefsnum);

    // ⚠ SP-driven for assigning legal refs: assign_legalrefs_to_grievance
}
