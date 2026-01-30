package com.gms.dto;

import java.time.LocalDateTime;

public class InvestigationResponseDTO {

    private String investigationNum;
    private String grvnNum;
    private String findings;
    private String remarks;
    private String outcome;
    private LocalDateTime investigationDate;
    private LocalDateTime endDate;

    public String getInvestigationNum() {
        return investigationNum;
    }

    public void setInvestigationNum(String investigationNum) {
        this.investigationNum = investigationNum;
    }

    public String getGrvnNum() {
        return grvnNum;
    }

    public void setGrvnNum(String grvnNum) {
        this.grvnNum = grvnNum;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
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
}
