package com.gms.repositories;

import com.gms.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    boolean existsByUserNum(String userNum);

    Admin findByUserNum(String adminNum);
}
