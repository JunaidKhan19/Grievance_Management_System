package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/*
 * This is the LegRefs entity class
 */

@Entity
@Table(name = "legalrefs")
public class LegalRefs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer legalrefsId;

    @Column(name = "legrefsnum", length = 4, unique = true, nullable = false, columnDefinition = "CHAR(6)")
    private String legRefsNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctgnum", referencedColumnName = "ctgNum", nullable = false, columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Category category;

    @Column(length = 30, nullable = false)
    private String topic;

    @Column(name = "actname", length = 30, nullable = false)
    private String actName;

    @Column(columnDefinition = "TEXT")
    private String legref;

    // Getters and Setters
    public Integer getLegalrefsId() { return legalrefsId; }
    public void setLegalrefsId(Integer legalrefsId) { this.legalrefsId = legalrefsId; }

    public String getLegRefsNum() { return legRefsNum; }
    public void setLegRefsNum(String legRefsNum) { this.legRefsNum = legRefsNum; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getActName() { return actName; }
    public void setActName(String actName) { this.actName = actName; }

    public String getLegRef() { return legref; }
    public void setLegRef(String legRef) { this.legref = legRef; }
}
