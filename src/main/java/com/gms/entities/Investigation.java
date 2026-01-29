package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * This is the Investigation entity class
 */

@Entity
@Table(name = "investigations")
public class Investigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer investigationId;

    @Column(name = "investigationnum", length = 4, unique = true, columnDefinition = "CHAR(4)")
    private String investigationNum;

    @ManyToOne
    @JoinColumn(name = "grvnnum", referencedColumnName = "grvnNum", columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Grievance grievance;

    @ManyToOne
    @JoinColumn(name = "officernum", referencedColumnName = "officernum", columnDefinition = "CHAR(4)")
    private Officer officer;

    @Column(length = 255)
    private String findings;

    @Column(name = "investigation_date", nullable = false)
    private LocalDateTime investigationDate;

    // Getters and Setters
    public Integer getInvestigationId() { return investigationId; }
    public void setInvestigationId(Integer investigationId) { this.investigationId = investigationId; }

    public String getInvestigationNum() { return investigationNum; }
    public void setInvestigationNum(String investigationNum) { this.investigationNum = investigationNum; }

    public Grievance getGrievance() { return grievance; }
    public void setGrievance(Grievance grievance) { this.grievance = grievance; }

    public Officer getOfficer() { return officer; }
    public void setOfficer(Officer officer) { this.officer = officer; }

    public String getFindings() { return findings; }
    public void setFindings(String findings) { this.findings = findings; }

    public LocalDateTime getInvestigationDate() { return investigationDate; }
    public void setInvestigationDate(LocalDateTime investigationDate) { this.investigationDate = investigationDate; }

    @Override
    public String toString() {
        return "Investigation{" +
                "investigationId=" + investigationId +
                ", investigationNum='" + investigationNum + '\'' +
                ", findings='" + findings + '\'' +
                ", investigationDate=" + investigationDate +
                '}';
    }
}
