package com.gms.controllers;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.dto.LegalRefResponseDTO;
import com.gms.dto.view.GrievanceLegalRefsViewDTO;
import com.gms.services.LegalRefService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/legalrefs")
public class LegalRefController {

    private final LegalRefService legalRefService;

    public LegalRefController(LegalRefService legalRefService) {
        this.legalRefService = legalRefService;
    }

    // Assign a legal reference to a grievance
    @PreAuthorize("hasRole('OFFICER')")
    @PostMapping("/assign")
    public void assignLegalRefToGrievance(@RequestBody GrievanceLegalRefDTO dto) {
        legalRefService.assignLegalRefToGrievance(dto);
    }

    @GetMapping("/grievance/{grvnNum}")
    public GrievanceLegalRefsViewDTO getLegalRefsByGrievance(@PathVariable String grvnNum) {
        return legalRefService.getLegalRefsByGrievance(grvnNum);
    }

    @GetMapping("/all-legal-references")
    public List<LegalRefResponseDTO> getAllLegalRefs() {
        return legalRefService.getAllLegalRefs();
    }
}
