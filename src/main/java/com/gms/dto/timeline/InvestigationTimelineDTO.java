package com.gms.dto.timeline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvestigationTimelineDTO {

    private String investigationNum;
    private String findings;
    private String outcome;
    private String remarks;
    private LocalDateTime investigationDate;
    private LocalDateTime endDate;

    private List<AppealTimelineDTO> appeals = new ArrayList<>();

    // Getters and Setters
    public String getInvestigationNum() {
        return investigationNum;
    }

    public void setInvestigationNum(String investigationNum) {
        this.investigationNum = investigationNum;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getInvestigationDate() {
        return investigationDate;
    }

    public void setInvestigationDate(LocalDateTime investigationDate) {
        this.investigationDate = investigationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<AppealTimelineDTO> getAppeals() {
        return appeals;
    }

    public void setAppeals(List<AppealTimelineDTO> appeals) {
        this.appeals = appeals;
    }
}
