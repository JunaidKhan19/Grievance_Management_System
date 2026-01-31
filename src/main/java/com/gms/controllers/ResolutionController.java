package com.gms.controllers;

import com.gms.dto.ResolutionDTO;
import com.gms.services.GrievanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resolutions")
public class ResolutionController {

    private final GrievanceService grievanceService;

    public ResolutionController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @GetMapping("/{grvnNum}")
    public ResponseEntity<ResolutionDTO> getResolution(@PathVariable String grvnNum) {
        ResolutionDTO dto = grievanceService.getResolutionByGrievance(grvnNum);
        return ResponseEntity.ok(dto);
    }

}
