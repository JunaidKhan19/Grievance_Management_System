package com.gms.controllers;

import com.gms.dto.GrievanceAssignDTO;
import com.gms.dto.GrievanceBasicDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/grievances")
@PreAuthorize("hasRole('ADMIN')")
public class AdminGrievanceController {

    private final GrievanceService grievanceService;

    public AdminGrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    // View all grievances (VIEW)
    @GetMapping
    public ResponseEntity<List<GrievanceBasicDTO>> getAllGrievances(
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(
                grievanceService.getAllGrievances(null, status)
        );
    }

    // DELETE grievance (SP: delete_grievance) 🔒 ADMIN ONLY
    @DeleteMapping("/{grvnNum}")
    public ResponseEntity<?> deleteGrievance(
            @PathVariable String grvnNum,
            Authentication auth
    ) {
        grievanceService.deleteGrievance(grvnNum, auth.getName(), "ADMIN");
        return ResponseEntity.ok("Grievance deleted");
    }
}
