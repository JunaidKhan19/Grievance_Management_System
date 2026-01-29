package com.gms.repositories;

import com.gms.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByGrievance_GrvnNum(String grvnNum);

    //List<Feedback> findByEmployee_UserNum(String empNum);  // Optional: feedbacks given by employee
}
