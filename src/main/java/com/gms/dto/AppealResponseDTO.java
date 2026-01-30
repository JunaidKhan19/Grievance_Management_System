package com.gms.dto;

import java.time.LocalDateTime;

public class AppealResponseDTO {

    private String appealNum;
    private String grvnNum;
    private String investigationNum;
    private String appealContent;
    private LocalDateTime appealDate;

    public String getAppealNum() {
        return appealNum;
    }

    public void setAppealNum(String appealNum) {
        this.appealNum = appealNum;
    }

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

    public LocalDateTime getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(LocalDateTime appealDate) {
        this.appealDate = appealDate;
    }
}
