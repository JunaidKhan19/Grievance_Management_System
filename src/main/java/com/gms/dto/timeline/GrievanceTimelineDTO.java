package com.gms.dto.timeline;

import java.util.ArrayList;
import java.util.List;

public class GrievanceTimelineDTO {

    private String grvnNum;

    private List<InvestigationTimelineDTO> investigations = new ArrayList<>();
    private List<AppealTimelineDTO> grievanceLevelAppeals = new ArrayList<>();

    // Getters and Setters
    public String getGrvnNum() {
        return grvnNum;
    }

    public void setGrvnNum(String grvnNum) {
        this.grvnNum = grvnNum;
    }

    public List<InvestigationTimelineDTO> getInvestigations() {
        return investigations;
    }

    public void setInvestigations(List<InvestigationTimelineDTO> investigations) {
        this.investigations = investigations;
    }

    public List<AppealTimelineDTO> getGrievanceLevelAppeals() {
        return grievanceLevelAppeals;
    }

    public void setGrievanceLevelAppeals(List<AppealTimelineDTO> grievanceLevelAppeals) {
        this.grievanceLevelAppeals = grievanceLevelAppeals;
    }
}
