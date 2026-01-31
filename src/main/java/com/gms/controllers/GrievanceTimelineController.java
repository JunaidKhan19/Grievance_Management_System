package com.gms.controllers;

import com.gms.dto.timeline.GrievanceTimelineDTO;
import com.gms.services.GrievanceTimelineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grievance")
public class GrievanceTimelineController {

    private final GrievanceTimelineService timelineService;

    public GrievanceTimelineController(GrievanceTimelineService timelineService) {
        this.timelineService = timelineService;
    }

    /**
     * GET /grievance/{grvnNum}/timeline
     * Fetch full grievance timeline:
     *  - Investigations + their appeals
     *  - Grievance-level appeals at the end
     */
    @GetMapping("/{grvnNum}/timeline")
    public GrievanceTimelineDTO getGrievanceTimeline(@PathVariable String grvnNum) {
        return timelineService.getTimeline(grvnNum);
    }
}
