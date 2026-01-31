package com.gms.services.impl;

import com.gms.dto.*;
import com.gms.entities.Grievance;
import com.gms.entities.Resolution;
import com.gms.repositories.GrievanceRepository;
import com.gms.repositories.GrievanceStatusRepository;
import com.gms.repositories.ResolutionRepository;
import com.gms.services.GrievanceService;
import com.gms.utils.ActorContextHolder;
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
    private final GrievanceStatusRepository grievanceStatusRepository;
    private final ResolutionRepository resolutionRepository;

    public GrievanceServiceImpl(
            GrievanceRepository grievanceRepository,
            GrievanceStatusRepository grievanceStatusRepository,
            ResolutionRepository resolutionRepository // ADD THIS
    ) {
        this.grievanceRepository = grievanceRepository;
        this.grievanceStatusRepository = grievanceStatusRepository;
        this.resolutionRepository = resolutionRepository; // ADD THIS
    }

    @PersistenceContext
    private EntityManager em;

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
    public GrievanceDTO getGrievancesByNum(String grvnNum) {
        Object[] obj = grievanceRepository.findWithEmployee(grvnNum);
        return GrievanceMapper.toGrievanceDTO(obj); // mapping raw Object[] to DTO
    }

    @Override
    @Transactional(readOnly = true)
    public GrievanceDTO getGrievanceByNum(String grvnNum) {

        Grievance grievance = grievanceRepository.findByGrvnNum(grvnNum);

        if (grievance == null) {
            throw new RuntimeException("Grievance not found: " + grvnNum);
        }

        GrievanceDTO dto = new GrievanceDTO();

        dto.setGrvnNum(grievance.getGrvnNum());

        // inherited from User
        dto.setEmpNum(grievance.getEmployee().getUserNum());

        dto.setCategoryNum(grievance.getCategory().getCtgNum());
        dto.setCategoryName(grievance.getCategory().getCtgName());

        dto.setSubject(grievance.getSubject());
        dto.setDescription(grievance.getDescription());
        dto.setStatus(grievance.getStatus());
        dto.setSeverity(grievance.getSeverity());
        dto.setDateFiled(grievance.getDateFiled());

        return dto;
    }

    @Override
    public List<GrievanceBasicDTO> getAllGrievances(String empNum, String status) {
        List<Object[]> list = grievanceRepository.findAllBasicByEmpNumAndStatus(empNum, status);
        return list.stream()
                .map(GrievanceMapper::toGrievanceBasicDTO)
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
    public List<GrievanceCategoryDTO> listByCategory(String category) {
        List<Object[]> rawList = grievanceRepository.listByCategory(category);

        return rawList.stream().map(obj -> {
            GrievanceCategoryDTO dto = new GrievanceCategoryDTO();
            dto.setCategoryNum((String) obj[0]);
            dto.setCategoryName((String) obj[1]);
            dto.setGrievanceNum((String) obj[2]);
            dto.setSubject((String) obj[3]);
            dto.setDescription((String) obj[4]);
            dto.setStatus((String) obj[5]);
            dto.setSeverity((String) obj[6]);
            dto.setDateFiled(obj[7] != null ? ((java.sql.Timestamp) obj[7]).toLocalDateTime() : null);
            dto.setEmployeeNum((String) obj[8]);
            dto.setEmployeeName((String) obj[9]);
            return dto;
        }).collect(Collectors.toList());
    }

    // 8. Assign grievance → SP
    @Override
    @Transactional
    public String assignGrievance(GrievanceAssignDTO dto) {

        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        if (actorId == null || actorRole == null) {
            throw new RuntimeException("Actor context not initialized");
        }

        StoredProcedureQuery sp =
                em.createStoredProcedureQuery("assign_grievance");

        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);   // p_grvnnum
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);   // p_actor_id
        sp.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);   // p_actor_role
        sp.registerStoredProcedureParameter(4, String.class, ParameterMode.OUT);  // p_investigationnum

        sp.setParameter(1, dto.getGrvnNum());
        sp.setParameter(2, actorId);      // ✅ from context
        sp.setParameter(3, actorRole);    // ✅ from context

        sp.execute();

        return (String) sp.getOutputParameterValue(4);
    }



    // 9. Resolve grievance → SP
    @Override
    @Transactional
    public String resolveGrievance(GrievanceResolveDTO dto) {

        // Get actor info from security context / session
        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        StoredProcedureQuery sp = em.createStoredProcedureQuery("resolve_grievance");

        // Register all SP parameters
        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);   // p_grvnnum
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);   // p_resolution
        sp.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);   // p_actor_id
        sp.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);   // p_actor_role
        sp.registerStoredProcedureParameter(5, String.class, ParameterMode.OUT);  // p_resnnum

        System.out.println("Resolution content = " + dto.getResolution());

        // Set IN parameters
        sp.setParameter(1, dto.getGrvnNum());
        sp.setParameter(2, dto.getResolution());
        sp.setParameter(3, actorId);
        sp.setParameter(4, actorRole);

        sp.execute();

        // Fetch OUT parameter (generated resolution number)
        String resnNum = (String) sp.getOutputParameterValue(5);
        System.out.println("Generated Resolution Num = " + resnNum);

        return resnNum;
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

    @Override
    @Transactional
    public void employeeIntendedResolve(String grvnnum) {
        grievanceStatusRepository.employeeIntendedResolve(grvnnum);
    }

    @Override
    public ResolutionDTO getResolutionByGrievance(String grvnNum) {
        Resolution res = resolutionRepository.findByGrievance_GrvnNum(grvnNum)
                .orElseThrow(() -> new RuntimeException("Resolution not found for grievance: " + grvnNum));

        ResolutionDTO dto = new ResolutionDTO();
        dto.setResnNum(res.getResnNum());
        dto.setGrvnNum(res.getGrievance().getGrvnNum());
        dto.setResnContent(res.getResnContent());
        dto.setResnDate(res.getResnDate());

        return dto;
    }


}
