package com.gms.services.impl;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.dto.LegalRefResponseDTO;
import com.gms.dto.view.GrievanceLegalRefsViewDTO;
import com.gms.entities.Grievance;
import com.gms.entities.GrievanceLegalRefs;
import com.gms.repositories.GrievanceRepository;
import com.gms.repositories.sp.LegalRefSPRepository;
import com.gms.services.LegalRefService;
import com.gms.repositories.LegalRefsRepository;
import com.gms.utils.ActorContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LegalRefServiceImpl implements LegalRefService {

    private final LegalRefsRepository legalRefsRepository;
    private final LegalRefSPRepository legalRefSPRepository;
    private final GrievanceRepository grievanceRepository;

    public LegalRefServiceImpl(LegalRefSPRepository legalRefSPRepository,
                               GrievanceRepository grievanceRepository,
                               LegalRefsRepository legalRefsRepository) {
        this.legalRefSPRepository = legalRefSPRepository;
        this.grievanceRepository = grievanceRepository;
        this.legalRefsRepository = legalRefsRepository;
    }

    @Override
    public void assignLegalRefToGrievance(GrievanceLegalRefDTO dto) {

        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        if (actorId == null || actorRole == null) {
            throw new SecurityException("Unauthenticated actor");
        }

        legalRefSPRepository.assignLegalRefToGrievance(
                actorId,
                actorRole,
                dto.getGrvnNum(),
                dto.getLegrefsNum()
        );
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
                    lrDto.setCtgnum(lr.getCategory().getCtgNum());
                    lrDto.setTopic(lr.getTopic());
                    lrDto.setActName(lr.getActName());
                    lrDto.setLegRef(lr.getLegRef());
                    return lrDto;
                })
                .collect(Collectors.toList());

        dto.setLegalReferences(legalRefs);
        return dto;
    }

    @Override
    public List<LegalRefResponseDTO> getAllLegalRefs() {

        return legalRefsRepository.findAll()
                .stream()
                .map(lr -> {
                    LegalRefResponseDTO dto = new LegalRefResponseDTO();
                    dto.setLegRefsNum(lr.getLegRefsNum());
                    dto.setCtgnum(lr.getCategory().getCtgNum());
                    dto.setTopic(lr.getTopic());
                    dto.setActName(lr.getActName());
                    dto.setLegRef(lr.getLegRef());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
