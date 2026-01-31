package com.gms.repositories;

import com.gms.entities.LegalRefs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRefsRepository extends JpaRepository<LegalRefs, Integer> {

    //filtering codes using java can be written here
}
