package com.gms.dto;

public class InvestigationUpdateDTO {

    private String investigationNum;
    private String findings;
    private String remarks;
    private String outcome;

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
}
