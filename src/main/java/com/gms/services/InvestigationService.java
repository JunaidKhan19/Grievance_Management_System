package com.gms.services;

import com.gms.dto.*;

import java.util.List;

public interface InvestigationService {

    InvestigationResponseDTO addInvestigation(InvestigationRequestDTO dto);

    void updateInvestigation(InvestigationUpdateDTO dto);

    void endInvestigation(InvestigationEndDTO dto);

    List<InvestigationDTO> getAllInvestigations();

    List<InvestigationDTO> getMyInvestigations();
}
