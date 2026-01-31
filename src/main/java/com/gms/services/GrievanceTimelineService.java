package com.gms.services;

import com.gms.dto.timeline.*;
import com.gms.repositories.sp.GrievanceTimelineSPRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrievanceTimelineService {

    private final GrievanceTimelineSPRepository repo;

    public GrievanceTimelineService(GrievanceTimelineSPRepository repo) {
        this.repo = repo;
    }

    /**
     * Fetch full grievance timeline:
     *  - Investigations + their appeals
     *  - Grievance-level appeals at the end
     */
    public GrievanceTimelineDTO getTimeline(String grvnNum) {
        GrievanceTimelineDTO timeline = new GrievanceTimelineDTO();
        timeline.setGrvnNum(grvnNum);

        // Map to preserve investigation order
        Map<String, InvestigationTimelineDTO> investigationMap = new LinkedHashMap<>();

        // Fetch investigations + their appeals
        List<Object[]> invRows = repo.fetchInvestigationAppeals(grvnNum);

        for (Object[] row : invRows) {
            String invNum = (String) row[0];

            // Create or get InvestigationTimelineDTO
            InvestigationTimelineDTO inv = investigationMap.computeIfAbsent(invNum, k -> {
                InvestigationTimelineDTO dto = new InvestigationTimelineDTO();
                dto.setInvestigationNum(invNum);
                dto.setFindings((String) row[1]);
                dto.setOutcome((String) row[2]);
                dto.setRemarks((String) row[3]);
                dto.setInvestigationDate(row[4] != null ? ((Timestamp) row[4]).toLocalDateTime() : null);
                dto.setEndDate(row[5] != null ? ((Timestamp) row[5]).toLocalDateTime() : null);
                return dto;
            });

            // If there’s an appeal linked to this investigation
            if (row[6] != null) {
                AppealTimelineDTO appeal = new AppealTimelineDTO();
                appeal.setAppealNum((String) row[6]);
                appeal.setAppealContent((String) row[7]);
                appeal.setAppealDate(row[8] != null ? ((Timestamp) row[8]).toLocalDateTime() : null);

                inv.getAppeals().add(appeal);
            }
        }

        timeline.getInvestigations().addAll(investigationMap.values());

        // Fetch grievance-level appeals (not linked to any investigation)
        List<Object[]> grievanceAppeals = repo.fetchGrievanceAppeals(grvnNum);

        for (Object[] row : grievanceAppeals) {
            AppealTimelineDTO appeal = new AppealTimelineDTO();
            appeal.setAppealNum((String) row[0]);
            appeal.setAppealContent((String) row[1]);
            appeal.setAppealDate(row[2] != null ? ((Timestamp) row[2]).toLocalDateTime() : null);

            timeline.getGrievanceLevelAppeals().add(appeal);
        }

        return timeline;
    }
}
