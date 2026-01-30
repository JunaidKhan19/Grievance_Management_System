package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * This is the Appeal entity class
 */

@Entity
@Table(name = "appeals")
public class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appealId;

    @Column(name = "appealnum", length = 4, unique = true, columnDefinition = "CHAR(4)")
    private String appealNum;

    @Column(name = "appealdate", nullable = false)
    private LocalDateTime appealDate;

    @ManyToOne
    @JoinColumn(name = "grvnnum", referencedColumnName = "grvnNum", columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Grievance grievance;

    @ManyToOne
    @JoinColumn(name = "investigationnum", referencedColumnName = "investigationNum", columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Investigation investigation;

    @ManyToOne
    @JoinColumn(name = "empnum", referencedColumnName = "empnum", columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Employee employee;

    @Column(name = "appealContent", length = 255, nullable = false)
    private String appealContent;

    // Getters and Setters
    public Integer getAppealId() { return appealId; }
    public void setAppealId(Integer appealId) { this.appealId = appealId; }

    public String getAppealNum() { return appealNum; }
    public void setAppealNum(String appealNum) { this.appealNum = appealNum; }

    public LocalDateTime getAppealDate() { return appealDate; }
    public void setAppealDate(LocalDateTime appealDate) { this.appealDate = appealDate; }

    public Grievance getGrievance() { return grievance; }
    public void setGrievance(Grievance grievance) { this.grievance = grievance; }

    public String getAppealContent() { return appealContent; }
    public void setAppealContent(String appealContent) { this.appealContent = appealContent; }

    @Override
    public String toString() {
        return "Appeal{" +
                "appealId=" + appealId +
                ", appealNum='" + appealNum + '\'' +
                ", appealDate=" + appealDate +
                ", appealContent='" + appealContent + '\'' +
                '}';
    }
}
