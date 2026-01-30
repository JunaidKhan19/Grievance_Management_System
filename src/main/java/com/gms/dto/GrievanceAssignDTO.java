package com.gms.dto;

public class GrievanceAssignDTO {

    private String grvnNum;
    private String officerNum;
    private String actorId;
    private String actorRole;

    // Getters & Setters
    public String getGrvnNum() { return grvnNum; }
    public void setGrvnNum(String grvnNum) { this.grvnNum = grvnNum; }

    public String getActorId() { return actorId; }
    public void setActorId(String actorId) { this.actorId = actorId; }

    public String getActorRole() { return actorRole; }
    public void setActorRole(String actorRole) { this.actorRole = actorRole; }
}
