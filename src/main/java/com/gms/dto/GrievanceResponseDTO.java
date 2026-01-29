package com.gms.dto;

public class GrievanceResponseDTO {
    private String grievanceNum;
    private String categoryNum;
    private String subject;
    private String description;
    private String severity;
    private String status;
    private String assignedOfficer;

    // Getters & Setters
    public String getGrievanceNum() { return grievanceNum; }
    public void setGrievanceNum(String grievanceNum) { this.grievanceNum = grievanceNum; }

    public String getCategoryNum() { return categoryNum; }
    public void setCategoryNum(String categoryNum) { this.categoryNum = categoryNum; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedOfficer() { return assignedOfficer; }
    public void setAssignedOfficer(String assignedOfficer) { this.assignedOfficer = assignedOfficer; }
}
