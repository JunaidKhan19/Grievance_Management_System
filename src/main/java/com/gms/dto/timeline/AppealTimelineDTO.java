package com.gms.dto.timeline;

import java.time.LocalDateTime;

public class AppealTimelineDTO {

    private String appealNum;
    private String appealContent;
    private LocalDateTime appealDate;

    // Getters and Setters
    public String getAppealNum() {
        return appealNum;
    }

    public void setAppealNum(String appealNum) {
        this.appealNum = appealNum;
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
