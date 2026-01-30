package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/*
 * This is the Grievance to LegalRefs entity class
 */
@Entity
@Table(name = "grievances_legalrefs", uniqueConstraints = {@UniqueConstraint(columnNames = {"grvnnum", "legrefsnum"})})
public class GrievanceLegalRefs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grievToRefsId")
    private Integer grievToRefsId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grvnnum", referencedColumnName = "grvnnum", nullable = false, columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Grievance grievance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "legrefsnum", referencedColumnName = "legrefsnum", nullable = false, columnDefinition = "CHAR(6)")
    private LegalRefs legalRefs;

    // Getters and Setters
    public Integer getGrievToRefsId() { return grievToRefsId; }
    public void setGrievToRefsId(Integer grievToRefsId) { this.grievToRefsId = grievToRefsId; }

    public Grievance getGrievance() { return grievance; }
    public void setGrievance(Grievance grievance) { this.grievance = grievance; }

    public LegalRefs getLegalRefs() { return legalRefs; }
    public void setLegalRefs(LegalRefs legalRefs) { this.legalRefs = legalRefs; }

    @Override
    public String toString() {
        return "GrievanceLegalRefs{" +
                "grievToRefsId=" + grievToRefsId +
                ", legalRefs=" + (legalRefs != null ? legalRefs.getLegRefsNum() : null) +
                '}';
    }
}
