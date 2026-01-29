package com.gms.repositories;

import com.gms.entities.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealRepository extends JpaRepository<Appeal, Integer> {

    List<Appeal> findByGrievance_GrvnNum(String grvnNum);

    // ⚠ SP-driven: filing appeals (if implemented)
}
