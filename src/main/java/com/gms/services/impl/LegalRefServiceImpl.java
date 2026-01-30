package com.gms.services.impl;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.dto.LegalRefResponseDTO;
import com.gms.dto.view.GrievanceLegalRefsViewDTO;
import com.gms.entities.Grievance;
import com.gms.entities.GrievanceLegalRefs;
import com.gms.repositories.GrievanceRepository;
import com.gms.repositories.sp.LegalRefSPRepository;
import com.gms.services.LegalRefService;
import com.gms.utils.ActorContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LegalRefServiceImpl implements LegalRefService {

    private final LegalRefSPRepository legalRefSPRepository;
    private final GrievanceRepository grievanceRepository;

    public LegalRefServiceImpl(LegalRefSPRepository legalRefSPRepository,
                               GrievanceRepository grievanceRepository) {
        this.legalRefSPRepository = legalRefSPRepository;
        this.grievanceRepository = grievanceRepository;
    }

    @Override
    public void assignLegalRefToGrievance(GrievanceLegalRefDTO dto) {
        legalRefSPRepository.assignLegalRefToGrievance(dto);
    }

    @Override
    public GrievanceLegalRefsViewDTO getLegalRefsByGrievance(String grvnNum) {
        Grievance grievance = grievanceRepository.findByGrvnNum(grvnNum);

        if (grievance == null) {
            throw new RuntimeException("Grievance not found with grvnNum: " + grvnNum);
        }

        GrievanceLegalRefsViewDTO dto = new GrievanceLegalRefsViewDTO();
        dto.setGrvnNum(grievance.getGrvnNum());

        List<LegalRefResponseDTO> legalRefs = grievance.getGrievanceLegalRefs()
                .stream()
                .map(GrievanceLegalRefs::getLegalRefs)
                .map(lr -> {
                    LegalRefResponseDTO lrDto = new LegalRefResponseDTO();
                    lrDto.setLegRefsNum(lr.getLegRefsNum());
                    lrDto.setTopic(lr.getTopic());
                    lrDto.setActName(lr.getActName());
                    lrDto.setLegRef(lr.getLegRef());
                    return lrDto;
                })
                .collect(Collectors.toList());

        dto.setLegalReferences(legalRefs);
        return dto;
    }
}
