package com.gms.dto;

import com.gms.enums.GrievanceStatus;
import com.gms.enums.Severity;
import java.time.LocalDateTime;

public class GrievanceBasicDTO {

    private String grvnNum;
    private String empNum;
    private String empName;
    private String categoryNum;
    private String categoryName;
    private String subject;
    private GrievanceStatus status;
    private Severity severity;
    private LocalDateTime dateFiled;

    // Getters & Setters
    public String getGrvnNum() { return grvnNum; }
    public void setGrvnNum(String grvnNum) { this.grvnNum = grvnNum; }

    public String getEmpNum() { return empNum; }
    public void setEmpNum(String empNum) { this.empNum = empNum; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getCategoryNum() { return categoryNum; }
    public void setCategoryNum(String categoryNum) { this.categoryNum = categoryNum; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public GrievanceStatus getStatus() { return status; }
    public void setStatus(GrievanceStatus status) { this.status = status; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public LocalDateTime getDateFiled() { return dateFiled; }
    public void setDateFiled(LocalDateTime dateFiled) { this.dateFiled = dateFiled; }
}
