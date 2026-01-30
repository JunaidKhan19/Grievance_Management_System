package com.gms.controllers;

import com.gms.dto.*;
import com.gms.services.InvestigationService;
import com.gms.utils.ActorContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/officer/investigations")
@PreAuthorize("hasRole('OFFICER')")
public class InvestigationController {

    private final InvestigationService investigationService;

    public InvestigationController(InvestigationService investigationService) {
        this.investigationService = investigationService;
    }

    // Get all investigations
    @GetMapping
    public ResponseEntity<List<InvestigationDTO>> getAllInvestigations() {
        return ResponseEntity.ok(investigationService.getAllInvestigations());
    }

    // Get own investigations
    @GetMapping("/my")
    public ResponseEntity<List<InvestigationDTO>> getMyInvestigations() {
        return ResponseEntity.ok(
                investigationService.getMyInvestigations()
        );
    }

    // Add a new investigation
    @PostMapping("/add")
    public InvestigationResponseDTO addInvestigation(@RequestBody InvestigationRequestDTO dto) {
        System.out.println("ActorId: " + ActorContextHolder.getActorId());
        System.out.println("ActorRole: " + ActorContextHolder.getActorRole());
        return investigationService.addInvestigation(dto);
    }

    // Update investigation
    @PutMapping("/{investigationNum}/update")
    public ResponseEntity<?> updateInvestigation(@PathVariable String investigationNum,
                                                 @RequestBody InvestigationUpdateDTO dto) {
        dto.setInvestigationNum(investigationNum);
        investigationService.updateInvestigation(dto);
        return ResponseEntity.ok("Investigation updated successfully");
    }

    // End investigation
    @PutMapping("/{investigationNum}/end")
    public ResponseEntity<?> endInvestigation(
            @PathVariable String investigationNum
    ) {
        InvestigationEndDTO dto = new InvestigationEndDTO();
        dto.setInvestigationNum(investigationNum);

        investigationService.endInvestigation(dto);

        return ResponseEntity.ok("Investigation ended successfully");
    }

}
