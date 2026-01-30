package com.gms.controllers.analytics;

import com.gms.dto.GrievanceCategoryDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grievances/analytics")
@PreAuthorize("hasAnyRole('ADMIN', 'OFFICER')")
public class GrievanceAnalyticsController {

    private final GrievanceService grievanceService;

    public GrievanceAnalyticsController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    // vw_grievances_by_status
    @GetMapping("/by-status")
    public ResponseEntity<?> countByStatus() {
        return ResponseEntity.ok(grievanceService.countByStatus());
    }

    // vw_grievances_by_category
    @GetMapping("/by-category")
    public ResponseEntity<?> countByCategory() {
        return ResponseEntity.ok(grievanceService.countByCategory());
    }

    // vw_grievances_list_by_category
    @GetMapping("/list-by-category")
    public ResponseEntity<?> listByCategory(@RequestParam(required = false) String category) {
        List<GrievanceCategoryDTO> grievances = grievanceService.listByCategory(category);
        return ResponseEntity.ok(grievances);
    }

}
