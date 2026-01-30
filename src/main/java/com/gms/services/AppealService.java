package com.gms.services;

import com.gms.dto.AppealRequestDTO;
import com.gms.dto.AppealResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppealService {

    AppealResponseDTO fileAppeal(AppealRequestDTO dto);

    List<AppealResponseDTO> getAppealsByInvestigation(String investigationNum);

    List<AppealResponseDTO> getMyAppeals();
}
