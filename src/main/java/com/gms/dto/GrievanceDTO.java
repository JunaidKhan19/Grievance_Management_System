package com.gms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gms.enums.GrievanceStatus;
import com.gms.enums.Severity;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GrievanceDTO {

    private String grvnNum;
    private String empNum;
    private String empName;
    private String empEmail;
    private String department;
    private String categoryNum;
    private String categoryName;
    private String subject;
    private String description;
    private GrievanceStatus status;
    private Severity severity;
    private LocalDateTime dateFiled;

    private String actorId;    // for SP calls
    private String actorRole;  // for SP calls

    // Getters & Setters
    public String getGrvnNum() { return grvnNum; }
    public void setGrvnNum(String grvnNum) { this.grvnNum = grvnNum; }

    public String getEmpNum() { return empNum; }
    public void setEmpNum(String empNum) { this.empNum = empNum; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCategoryNum() { return categoryNum; }
    public void setCategoryNum(String categoryNum) { this.categoryNum = categoryNum; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public GrievanceStatus getStatus() { return status; }
    public void setStatus(GrievanceStatus status) { this.status = status; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public LocalDateTime getDateFiled() { return dateFiled; }
    public void setDateFiled(LocalDateTime dateFiled) { this.dateFiled = dateFiled; }

    public String getActorId() { return actorId; }
    public void setActorId(String actorId) { this.actorId = actorId; }

    public String getActorRole() { return actorRole; }
    public void setActorRole(String actorRole) { this.actorRole = actorRole; }
}
