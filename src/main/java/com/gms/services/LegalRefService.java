package com.gms.services;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.dto.LegalRefResponseDTO;
import com.gms.dto.view.GrievanceLegalRefsViewDTO;

import java.util.List;

public interface LegalRefService {

    void assignLegalRefToGrievance(GrievanceLegalRefDTO dto);

    GrievanceLegalRefsViewDTO getLegalRefsByGrievance(String grvnNum);

    List<LegalRefResponseDTO> getAllLegalRefs();
}
