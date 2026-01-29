package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * This is the Feedback entity class
 */

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @Column(length = 4, unique = true, columnDefinition = "CHAR(4)")
    private String feedbackNum;

    @ManyToOne
    @JoinColumn(name = "grvnNum", referencedColumnName = "grvnNum", columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Grievance grievance;

    @Column(length = 255, nullable = false)
    private String empResponse;

    private Integer rating; // max 5

    @Column(name = "feedback_date", nullable = false)
    private LocalDateTime feedbackDate;

    // Getters and Setters
    public Integer getFeedbackId() { return feedbackId; }
    public void setFeedbackId(Integer feedbackId) { this.feedbackId = feedbackId; }

    public String getFeedbackNum() { return feedbackNum; }
    public void setFeedbackNum(String feedbackNum) { this.feedbackNum = feedbackNum; }

    public Grievance getGrievance() { return grievance; }
    public void setGrievance(Grievance grievance) { this.grievance = grievance; }

    public String getEmpResponse() { return empResponse; }
    public void setEmpResponse(String empResponse) { this.empResponse = empResponse; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public LocalDateTime getFeedbackDate() { return feedbackDate; }
    public void setFeedbackDate(LocalDateTime feedbackDate) { this.feedbackDate = feedbackDate; }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", feedbackNum='" + feedbackNum + '\'' +
                ", empResponse='" + empResponse + '\'' +
                ", rating=" + rating +
                ", feedbackDate=" + feedbackDate +
                '}';
    }
}
