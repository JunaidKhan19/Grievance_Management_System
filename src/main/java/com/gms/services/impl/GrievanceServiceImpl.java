package com.gms.services.impl;

import com.gms.dto.*;
import com.gms.repositories.GrievanceRepository;
import com.gms.services.GrievanceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrievanceServiceImpl implements GrievanceService {

    private final GrievanceRepository grievanceRepository;

    @PersistenceContext
    private EntityManager em;

    public GrievanceServiceImpl(GrievanceRepository grievanceRepository) {
        this.grievanceRepository = grievanceRepository;
    }

    @Override
    @Transactional
    public GrievanceResponseDTO fileGrievance(GrievanceRequestDTO request, String actorId, String actorRole) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery("file_grievance");
        sp.registerStoredProcedureParameter("p_ctgnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_subject", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_description", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_severity", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_grvnnum_out", String.class, ParameterMode.OUT);

        sp.setParameter("p_ctgnum", request.getCategoryNum());
        sp.setParameter("p_subject", request.getSubject());
        sp.setParameter("p_description", request.getDescription());
        sp.setParameter("p_severity", request.getSeverity());
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        sp.execute();

        String generatedGrvnNum = (String) sp.getOutputParameterValue("p_grvnnum_out");

        GrievanceResponseDTO response = new GrievanceResponseDTO();
        response.setGrievanceNum(generatedGrvnNum);
        response.setCategoryNum(request.getCategoryNum());
        response.setSubject(request.getSubject());
        response.setDescription(request.getDescription());
        response.setSeverity(request.getSeverity());
        response.setStatus("PENDING");
        response.setAssignedOfficer(null);

        return response;
    }


    // 2. Fetch single grievance
    @Override
    public GrievanceDTO getGrievanceByNum(String grvnNum) {
        Object[] obj = grievanceRepository.findWithEmployee(grvnNum);
        return GrievanceMapper.toGrievanceDTO(obj); // mapping raw Object[] to DTO
    }

    @Override
    public List<GrievanceBasicDTO> getAllGrievances(String empNum, String status) {
        List<Object[]> list = grievanceRepository.findAllBasicByEmpNumAndStatus(empNum, status);
        return list.stream()
                .map(GrievanceMapper::toGrievanceBasicDTO)
                .collect(Collectors.toList());
    }

    // 4. Resolved grievances
    @Override
    public List<GrievanceDTO> getResolvedGrievances() {
        return grievanceRepository.resolvedGrievances().stream()
                .map(GrievanceMapper::toGrievanceDTO)
                .collect(Collectors.toList());
    }

    // 5 & 6 & 7: Dashboard queries
    @Override
    public List<Object[]> countByStatus() {
        return grievanceRepository.countByStatus();
    }

    @Override
    public List<Object[]> countByCategory() {
        return grievanceRepository.countByCategory();
    }

    @Override
    public List<Object[]> listByCategory() {
        return grievanceRepository.listByCategory();
    }

    // 8. Assign grievance → SP
    @Override
    @Transactional
    public void assignGrievance(GrievanceAssignDTO dto) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery("assign_grievance");
        sp.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_officernum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        sp.setParameter("p_grvnnum", dto.getGrvnNum());
        sp.setParameter("p_officernum", dto.getOfficerNum());
        sp.setParameter("p_actor_id", dto.getActorId());
        sp.setParameter("p_actor_role", dto.getActorRole());

        sp.execute();
    }

    // 9. Resolve grievance → SP
    @Override
    @Transactional
    public void resolveGrievance(GrievanceResolveDTO dto) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery("resolve_grievance");
        sp.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        sp.setParameter("p_grvnnum", dto.getGrvnNum());
        sp.setParameter("p_actor_id", dto.getActorId());
        sp.setParameter("p_actor_role", dto.getActorRole());

        sp.execute();
    }

    // 10. Delete grievance → SP
    @Override
    @Transactional
    public void deleteGrievance(String grvnNum, String actorId, String actorRole) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery("delete_grievance");

        sp.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        sp.setParameter("p_grvnnum", grvnNum);
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        sp.execute();
    }

    // 11. Exists
    @Override
    public boolean exists(String grvnNum) {
        return grievanceRepository.existsByGrvnNum(grvnNum);
    }
}
