package com.gms.dto;

public class AppealRequestDTO {

    private String grvnNum;           // optional
    private String investigationNum;  // optional
    private String appealContent;

    public String getGrvnNum() {
        return grvnNum;
    }

    public void setGrvnNum(String grvnNum) {
        this.grvnNum = grvnNum;
    }

    public String getInvestigationNum() {
        return investigationNum;
    }

    public void setInvestigationNum(String investigationNum) {
        this.investigationNum = investigationNum;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }
}
