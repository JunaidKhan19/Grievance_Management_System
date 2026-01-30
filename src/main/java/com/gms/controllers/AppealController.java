package com.gms.controllers;

import com.gms.dto.AppealRequestDTO;
import com.gms.dto.AppealResponseDTO;
import com.gms.services.AppealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appeals")
public class AppealController {

    private final AppealService appealService;

    public AppealController(AppealService appealService) {
        this.appealService = appealService;
    }

    // File an appeal (employee only)
    @PostMapping("/file")
    public AppealResponseDTO fileAppeal(@RequestBody AppealRequestDTO dto) {
        return appealService.fileAppeal(dto);
    }

    // Get appeals by investigation
    @GetMapping("/investigation/{investigationNum}")
    public List<AppealResponseDTO> getByInvestigation(@PathVariable String investigationNum) {
        return appealService.getAppealsByInvestigation(investigationNum);
    }

    // Get logged-in employee's appeals
    @GetMapping("/my")
    public List<AppealResponseDTO> getMyAppeals() {
        return appealService.getMyAppeals();
    }
}
