package com.gms.controllers;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.dto.view.GrievanceLegalRefsViewDTO;
import com.gms.services.LegalRefService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/legalrefs")
public class LegalRefController {

    private final LegalRefService legalRefService;

    public LegalRefController(LegalRefService legalRefService) {
        this.legalRefService = legalRefService;
    }

    // Assign a legal reference to a grievance
    @PostMapping("/assign")
    public void assignLegalRefToGrievance(@RequestBody GrievanceLegalRefDTO dto) {
        legalRefService.assignLegalRefToGrievance(dto);
    }

    @GetMapping("/{grvnNum}")
    public GrievanceLegalRefsViewDTO getLegalRefsByGrievance(@PathVariable String grvnNum) {
        return legalRefService.getLegalRefsByGrievance(grvnNum);
    }
}
