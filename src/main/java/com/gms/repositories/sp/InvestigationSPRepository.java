package com.gms.repositories.sp;

import com.gms.dto.InvestigationEndDTO;
import com.gms.dto.InvestigationRequestDTO;
import com.gms.dto.InvestigationResponseDTO;
import com.gms.dto.InvestigationUpdateDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class InvestigationSPRepository {

    @PersistenceContext
    private EntityManager em;

    // Add investigation
    public InvestigationResponseDTO addInvestigation(
            InvestigationRequestDTO dto,
            String actorId,
            String actorRole
    ) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery("add_investigation");

        sp.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_findings", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_remarks", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_outcome", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_investigationnum", String.class, ParameterMode.OUT);

        sp.setParameter("p_grvnnum", dto.getGrvnNum());
        sp.setParameter("p_findings", dto.getFindings());
        sp.setParameter("p_remarks", dto.getRemarks());
        sp.setParameter("p_outcome", dto.getOutcome());
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        sp.execute();

        String investigationNum = (String) sp.getOutputParameterValue("p_investigationnum");

        // Build DTO response
        InvestigationResponseDTO response = new InvestigationResponseDTO();
        response.setInvestigationNum(investigationNum);
        response.setGrvnNum(dto.getGrvnNum());
        response.setFindings(dto.getFindings());
        response.setRemarks(dto.getRemarks());
        response.setOutcome(dto.getOutcome());
        response.setInvestigationDate(LocalDateTime.now()); // optional, could fetch from DB if needed

        return response;
    }

    // Update investigation
    public void updateInvestigation(
            InvestigationUpdateDTO dto,
            String actorId,
            String actorRole
    ) {
        StoredProcedureQuery sp = em.createStoredProcedureQuery("update_investigation");

        sp.registerStoredProcedureParameter("p_investigationnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_findings", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_remarks", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_outcome", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        sp.setParameter("p_investigationnum", dto.getInvestigationNum());
        sp.setParameter("p_findings", dto.getFindings());
        sp.setParameter("p_remarks", dto.getRemarks());
        sp.setParameter("p_outcome", dto.getOutcome());
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        sp.execute();
    }

    // End investigation
    public void endInvestigation(
            String investigationNum,
            String actorId,
            String actorRole,
            LocalDateTime endDate
    ) {
        StoredProcedureQuery sp =
                em.createStoredProcedureQuery("sp_end_investigation");

        sp.registerStoredProcedureParameter("p_investigationnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_enddate", LocalDateTime.class, ParameterMode.IN);

        sp.setParameter("p_investigationnum", investigationNum);
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);
        sp.setParameter("p_enddate", endDate);

        sp.execute();
    }

}
