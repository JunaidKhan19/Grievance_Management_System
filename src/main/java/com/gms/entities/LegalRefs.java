package com.gms.entities;

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

    @Column(name = "legrefsnum", length = 4, unique = true, nullable = false, columnDefinition = "CHAR(4)")
    private String legRefsnum;

    @Column(length = 30, nullable = false)
    private String topic;

    @Column(name = "actname", length = 30, nullable = false)
    private String actName;

    @Column(columnDefinition = "TEXT")
    private String legref;

    // Getters and Setters
    public Integer getLegalrefsId() { return legalrefsId; }
    public void setLegalrefsId(Integer legalrefsId) { this.legalrefsId = legalrefsId; }

    public String getLegRefsNum() { return legRefsnum; }
    public void setLegRefsNum(String legRefsNum) { this.legRefsnum = legRefsNum; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getActName() { return actName; }
    public void setActName(String actName) { this.actName = actName; }

    public String getLegRef() { return legref; }
    public void setLegRef(String legRef) { this.legref = legRef; }
}
