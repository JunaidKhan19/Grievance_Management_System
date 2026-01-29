package com.gms.controllers;

import com.gms.dto.GrievanceAssignDTO;
import com.gms.dto.GrievanceBasicDTO;
import com.gms.dto.GrievanceResolveDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(
                grievanceService.getAllGrievances(null, status)
        );
    }

    // Assign grievance (SP: assign_grievance)
    @PutMapping("/{grvnNum}/assign")
    public ResponseEntity<?> assignGrievance(
            @PathVariable String grvnNum,
            @RequestBody GrievanceAssignDTO dto,
            Authentication auth
    ) {
        dto.setGrvnNum(grvnNum);
        dto.setActorId(auth.getName());
        dto.setActorRole("OFFICER");

        grievanceService.assignGrievance(dto);
        return ResponseEntity.ok("Grievance assigned");
    }

    // Resolve grievance (SP: resolve_grievance)
    @PutMapping("/{grvnNum}/resolve")
    public ResponseEntity<?> resolveGrievance(
            @PathVariable String grvnNum,
            @RequestBody GrievanceResolveDTO dto,
            Authentication auth
    ) {
        dto.setGrvnNum(grvnNum);
        dto.setActorId(auth.getName());
        dto.setActorRole("OFFICER");

        grievanceService.resolveGrievance(dto);
        return ResponseEntity.ok("Grievance resolved");
    }
}
