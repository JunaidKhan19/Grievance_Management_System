package com.gms.dto;

import java.time.LocalDateTime;

public class ResolutionDTO {

    private String resnNum;
    private String grvnNum;
    private String resnContent;
    private LocalDateTime resnDate;

    // Getters & Setters
    public String getResnNum() {
        return resnNum;
    }

    public void setResnNum(String resnNum) {
        this.resnNum = resnNum;
    }

    public String getGrvnNum() {
        return grvnNum;
    }

    public void setGrvnNum(String grvnNum) {
        this.grvnNum = grvnNum;
    }

    public String getResnContent() {
        return resnContent;
    }

    public void setResnContent(String resnContent) {
        this.resnContent = resnContent;
    }

    public LocalDateTime getResnDate() {
        return resnDate;
    }

    public void setResnDate(LocalDateTime resnDate) {
        this.resnDate = resnDate;
    }
}
