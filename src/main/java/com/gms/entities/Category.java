package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

/*
 * This is the Category entity class
 */

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ctgId;

    @Column(name = "ctgnum", length = 4, unique = true, nullable = false, columnDefinition = "CHAR(4)")
    private String ctgNum;

    @Column(name = "ctgname", length = 30, unique = true, nullable = false)
    private String ctgName;

    @Column(name = "ctgdesc", columnDefinition = "TEXT")
    private String ctgDesc;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Grievance> grievances;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Officer> officers;

    // Getters and Setters
    public Integer getCtgId() { return ctgId; }
    public void setCtgId(Integer ctgId) { this.ctgId = ctgId; }

    public String getCtgNum() { return ctgNum; }
    public void setCtgNum(String ctgNum) { this.ctgNum = ctgNum; }

    public String getCtgName() { return ctgName; }
    public void setCtgName(String ctgName) { this.ctgName = ctgName; }

    public String getCtgDesc() { return ctgDesc; }
    public void setCtgDesc(String ctgDesc) { this.ctgDesc = ctgDesc; }

    public Set<Officer> getOfficers() { return officers; }
    public void setOfficers(Set<Officer> officers) { this.officers = officers; }

    public Set<Grievance> getGrievances() { return grievances; }
    public void setGrievances(Set<Grievance> grievances) { this.grievances = grievances; }
}
