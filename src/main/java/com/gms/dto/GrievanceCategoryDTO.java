package com.gms.dto;

import java.time.LocalDateTime;

public class GrievanceCategoryDTO {

    private String grievanceNum;   // g.grvnnum
    private String subject;        // g.subject
    private String description;    // g.description
    private String categoryNum;    // c.ctgnum
    private String categoryName;   // c.ctgname
    private String status;         // g.status
    private String severity;       // g.severity
    private LocalDateTime dateFiled; // g.datefiled
    private String employeeNum;    // e.empnum
    private String employeeName;   // e.empname

    // Getters and Setters
    public String getGrievanceNum() {
        return grievanceNum;
    }

    public void setGrievanceNum(String grievanceNum) {
        this.grievanceNum = grievanceNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(String categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public LocalDateTime getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(LocalDateTime dateFiled) {
        this.dateFiled = dateFiled;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
