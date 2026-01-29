package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * This is the Resolution entity class
 */

@Entity
@Table(name = "resolutions")
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resnId;

    @Column(length = 4, unique = true, columnDefinition = "CHAR(4)")
    private String resnNum;

    @ManyToOne
    @JoinColumn(name = "grvnnum", referencedColumnName = "grvnNum", columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Grievance grievance;

    @Column(length = 250, nullable = false)
    private String resnContent;

    private LocalDateTime resnDate;

    // Getters and Setters
    public Integer getResnId() { return resnId; }
    public void setResnId(Integer resnId) { this.resnId = resnId; }

    public String getResnNum() { return resnNum; }
    public void setResnNum(String resnNum) { this.resnNum = resnNum; }

    public Grievance getGrievance() { return grievance; }
    public void setGrievance(Grievance grievance) { this.grievance = grievance; }

    public String getResnContent() { return resnContent; }
    public void setResnContent(String resnContent) { this.resnContent = resnContent; }

    public LocalDateTime getResnDate() { return resnDate; }
    public void setResnDate(LocalDateTime resnDate) { this.resnDate = resnDate; }

    @Override
    public String toString() {
        return "Resolution{" +
                "resnId=" + resnId +
                ", resnNum='" + resnNum + '\'' +
                ", resnContent='" + resnContent + '\'' +
                ", resnDate=" + resnDate +
                '}';
    }
}
