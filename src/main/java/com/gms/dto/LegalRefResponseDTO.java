package com.gms.dto;

public class LegalRefResponseDTO {

    private String legRefsNum;
    private String ctgnum;
    private String topic;
    private String actName;
    private String legRef;

    public String getLegRefsNum() {
        return legRefsNum;
    }

    public void setLegRefsNum(String legRefsNum) {
        this.legRefsNum = legRefsNum;
    }

    public String getCtgnum() {
        return ctgnum;
    }

    public void setCtgnum(String ctgnum) {
        this.ctgnum = ctgnum;
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getLegRef() {
        return legRef;
    }

    public void setLegRef(String legRef) {
        this.legRef = legRef;
    }
}
