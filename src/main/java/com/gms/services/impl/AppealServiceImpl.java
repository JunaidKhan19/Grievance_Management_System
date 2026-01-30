package com.gms.services.impl;

import com.gms.dto.AppealRequestDTO;
import com.gms.dto.AppealResponseDTO;
import com.gms.repositories.sp.AppealSPRepository;
import com.gms.services.AppealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppealServiceImpl implements AppealService {

    private final AppealSPRepository appealSPRepository;

    public AppealServiceImpl(AppealSPRepository appealSPRepository) {
        this.appealSPRepository = appealSPRepository;
    }

    @Override
    public AppealResponseDTO fileAppeal(AppealRequestDTO dto) {
        return appealSPRepository.fileAppeal(dto);
    }

    @Override
    public List<AppealResponseDTO> getAppealsByInvestigation(String investigationNum) {
        return appealSPRepository.getAppealsByInvestigation(investigationNum);
    }

    @Override
    public List<AppealResponseDTO> getMyAppeals() {
        return appealSPRepository.getMyAppeals();
    }
}
