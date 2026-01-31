package com.gms.controllers;

import com.gms.dto.GrievanceBasicDTO;
import com.gms.dto.GrievanceRequestDTO;
import com.gms.dto.GrievanceResponseDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/grievances")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeGrievanceController {

    private final GrievanceService grievanceService;

    public EmployeeGrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public ResponseEntity<GrievanceResponseDTO> fileGrievance(
            @RequestBody GrievanceRequestDTO dto,
            Authentication auth
    ) {
        try {
            System.out.println(">>> CONTROLLER ENTERED");

            // Just calling the service; aspect will set context
            GrievanceResponseDTO result = grievanceService.fileGrievance(
                    dto,
                    auth.getName(),
                    "EMPLOYEE"
            );

            System.out.println(">>> CONTROLLER EXITING");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GrievanceBasicDTO>> getMyGrievances(
            @RequestParam(required = false) String status,
            Authentication auth
    ) {
        // Aspect automatically sets actor context if service uses actorId/role
        return ResponseEntity.ok(
                grievanceService.getAllGrievances(auth.getName(), status)
        );
    }

    @PutMapping("/{grvnnum}/intended-resolve")
    public ResponseEntity<Void> intendedResolve(@PathVariable String grvnnum) {

        grievanceService.employeeIntendedResolve(grvnnum);

        return ResponseEntity.ok().build();
    }

}
