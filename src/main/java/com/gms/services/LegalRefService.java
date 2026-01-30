package com.gms.services;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.dto.view.GrievanceLegalRefsViewDTO;

public interface LegalRefService {

    void assignLegalRefToGrievance(GrievanceLegalRefDTO dto);

    GrievanceLegalRefsViewDTO getLegalRefsByGrievance(String grvnNum);
}
