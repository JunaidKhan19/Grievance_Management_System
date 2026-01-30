package com.gms.dto;

import java.time.LocalDateTime;

public class InvestigationDTO {

    private String investigationNum;
    private String grvnNum;
    private String officerNum;
    private String findings;
    private LocalDateTime investigationDate;

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

    public String getOfficerNum() {
        return officerNum;
    }

    public void setOfficerNum(String officerNum) {
        this.officerNum = officerNum;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public LocalDateTime getInvestigationDate() {
        return investigationDate;
    }

    public void setInvestigationDate(LocalDateTime investigationDate) {
        this.investigationDate = investigationDate;
    }
}
