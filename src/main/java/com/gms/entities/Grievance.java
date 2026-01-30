package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gms.enums.GrievanceStatus;
import com.gms.enums.Severity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/*
 * This is the Grievance entity class
 */

@Entity
@Table(name = "grievances")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer grvnId;

    @Column(name = "grvnnum", length = 4, unique = true, nullable = false, columnDefinition = "CHAR(4)")
    private String grvnNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empnum", referencedColumnName = "empnum", nullable = false, columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctgnum", referencedColumnName = "ctgNum", nullable = false, columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Category category;

    @Column(name = "datefiled")
    private LocalDateTime dateFiled;

    @Column(length = 250)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "status", length = 20, nullable = false)
    private GrievanceStatus status;

    @Column(name = "severity", length = 20, nullable = false)
    private Severity severity;

    @OneToMany(mappedBy = "grievance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<GrievanceLegalRefs> grievanceLegalRefs = new HashSet<>();

    @OneToMany(mappedBy = "grievance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Investigation> investigations = new HashSet<>();

    @OneToMany(mappedBy = "grievance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Appeal> appeals = new HashSet<>();

    @OneToMany(mappedBy = "grievance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Feedback> feedbacks = new HashSet<>();

    @OneToMany(mappedBy = "grievance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Resolution> resolutions = new HashSet<>();

    // Getters and Setters
    public Integer getGrvnId() { return grvnId; }
    public void setGrvnId(Integer grvnId) { this.grvnId = grvnId; }

    public String getGrvnNum() { return grvnNum; }
    public void setGrvnNum(String grvnNum) { this.grvnNum = grvnNum; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public LocalDateTime getDateFiled() { return dateFiled; }
    public void setDateFiled(LocalDateTime dateFiled) { this.dateFiled = dateFiled; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public GrievanceStatus getStatus() { return status; }
    public void setStatus(GrievanceStatus status) { this.status = status; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public Set<GrievanceLegalRefs> getGrievanceLegalRefs() {
        return grievanceLegalRefs;
    }

    public void setGrievanceLegalRefs(Set<GrievanceLegalRefs> grievanceLegalRefs) {
        this.grievanceLegalRefs = grievanceLegalRefs;
    }


    @Override
    public String toString() {
        return "Grievance{" +
                "grvnId=" + grvnId +
                ", grvnNum='" + grvnNum + '\'' +
                ", subject='" + subject + '\'' +
                ", status=" + status +
                ", severity=" + severity +
                '}';
    }
}
