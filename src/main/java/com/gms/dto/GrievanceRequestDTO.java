package com.gms.dto;

public class GrievanceRequestDTO {
    private String categoryNum;
    private String subject;
    private String description;
    private String severity;

    // Getters & Setters
    public String getCategoryNum() { return categoryNum; }
    public void setCategoryNum(String categoryNum) { this.categoryNum = categoryNum; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
}
