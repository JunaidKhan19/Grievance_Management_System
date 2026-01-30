package com.gms.services.impl;

import com.gms.dto.*;
import com.gms.entities.Investigation;
import com.gms.repositories.InvestigationRepository;
import com.gms.repositories.sp.InvestigationSPRepository;
import com.gms.services.InvestigationService;
import com.gms.utils.ActorContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvestigationServiceImpl implements InvestigationService {

    private final InvestigationSPRepository spRepository;
    private final InvestigationRepository investigationRepository;

    public InvestigationServiceImpl(InvestigationSPRepository spRepository,
                                    InvestigationRepository investigationRepository) {
        this.spRepository = spRepository;
        this.investigationRepository = investigationRepository;
    }

    @Override
    public List<InvestigationDTO> getAllInvestigations() {
        List<Investigation> investigations = investigationRepository.findAll();
        return investigations.stream().map(inv -> {
            InvestigationDTO dto = new InvestigationDTO();
            dto.setInvestigationNum(inv.getInvestigationNum());
            dto.setGrvnNum(inv.getGrievance() != null ? inv.getGrievance().getGrvnNum() : null);
            dto.setOfficerNum(inv.getOfficer() != null ? inv.getOfficer().getUserNum() : null);
            dto.setFindings(inv.getFindings());
            dto.setInvestigationDate(inv.getInvestigationDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<InvestigationDTO> getMyInvestigations() {

        String officerNum = ActorContextHolder.getActorId(); // O005

        List<Investigation> investigations =
                investigationRepository.findByOfficer_UserNum(officerNum);

        return investigations.stream().map(inv -> {
            InvestigationDTO dto = new InvestigationDTO();
            dto.setInvestigationNum(inv.getInvestigationNum());
            dto.setGrvnNum(
                    inv.getGrievance() != null
                            ? inv.getGrievance().getGrvnNum()
                            : null
            );
            dto.setOfficerNum(officerNum);
            dto.setFindings(inv.getFindings());
            dto.setInvestigationDate(inv.getInvestigationDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public InvestigationResponseDTO addInvestigation(InvestigationRequestDTO dto) {
        return spRepository.addInvestigation(
                dto,
                ActorContextHolder.getActorId(),
                ActorContextHolder.getActorRole()
        );
    }

    @Override
    public void updateInvestigation(InvestigationUpdateDTO dto) {
        spRepository.updateInvestigation(
                dto,
                ActorContextHolder.getActorId(),
                ActorContextHolder.getActorRole()
        );
    }

    @Override
    public void endInvestigation(InvestigationEndDTO dto) {

        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        LocalDateTime endDate = LocalDateTime.now(); // SERVER TIME

        spRepository.endInvestigation(
                dto.getInvestigationNum(),
                actorId,
                actorRole,
                endDate
        );
    }
}
