package com.gms.controllers;

import com.gms.dto.GrievanceAssignDTO;
import com.gms.dto.GrievanceBasicDTO;
import com.gms.dto.GrievanceResolveDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/officer/grievances")
@PreAuthorize("hasRole('OFFICER')")
public class OfficerGrievanceController {

    private final GrievanceService grievanceService;

    public OfficerGrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    // View all grievances (VIEW: vw_grievances_basic)
    @GetMapping
    public ResponseEntity<List<GrievanceBasicDTO>> getAllGrievances(
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(grievanceService.getAllGrievances(null, status));
    }

    // Assign grievance
    @PutMapping("/{grvnNum}/assign")
    public ResponseEntity<?> assignGrievance(@PathVariable String grvnNum) {

        GrievanceAssignDTO dto = new GrievanceAssignDTO();
        dto.setGrvnNum(grvnNum);

        String investigationNum = grievanceService.assignGrievance(dto);

        return ResponseEntity.ok(Map.of(
                "message", "Grievance assigned successfully",
                "grvnNum", grvnNum,
                "investigationNum", investigationNum
        ));
    }

    // Resolve grievance
    @PutMapping("/{grvnNum}/resolve")
    public ResponseEntity<?> resolveGrievance(@PathVariable String grvnNum,
                                              @RequestBody GrievanceResolveDTO dto) {

        dto.setGrvnNum(grvnNum); // only set grvnNum
        grievanceService.resolveGrievance(dto);

        return ResponseEntity.ok(Map.of(
                "message", "Grievance resolved successfully",
                "grvnNum", grvnNum
        ));
    }
}
