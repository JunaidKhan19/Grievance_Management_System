package com.gms.repositories.sp;

import com.gms.dto.AppealRequestDTO;
import com.gms.dto.AppealResponseDTO;
import com.gms.utils.ActorContextHolder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppealSPRepository {

    @PersistenceContext
    private EntityManager em;

    // File appeal
    public AppealResponseDTO fileAppeal(AppealRequestDTO dto) {
        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        StoredProcedureQuery sp = em.createStoredProcedureQuery("file_appeal");

        sp.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_investigationnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_appealContent", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_appealnum", String.class, ParameterMode.OUT);

        sp.setParameter("p_grvnnum", dto.getGrvnNum());
        sp.setParameter("p_investigationnum", dto.getInvestigationNum());
        sp.setParameter("p_appealContent", dto.getAppealContent());
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        sp.execute();
        String appealNum = (String) sp.getOutputParameterValue("p_appealnum");

        AppealResponseDTO response = new AppealResponseDTO();
        response.setAppealNum(appealNum);
        response.setGrvnNum(dto.getGrvnNum());
        response.setInvestigationNum(dto.getInvestigationNum());
        response.setAppealContent(dto.getAppealContent());
        response.setAppealDate(LocalDateTime.now());

        return response;
    }

    // Get appeals by investigation
    //Fetches appeals for ONE investigation
    //Enforces strict access control
    //EMPLOYEE → denied
    //OFFICER → only if assigned
    //ADMIN → allowed
    public List<AppealResponseDTO> getAppealsByInvestigation(String investigationNum) {
        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        StoredProcedureQuery sp = em.createStoredProcedureQuery("fetch_appeals_by_investigation");

        sp.registerStoredProcedureParameter("p_investigationnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        sp.setParameter("p_investigationnum", investigationNum);
        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        List<Object[]> results = sp.getResultList();
        List<AppealResponseDTO> appeals = new ArrayList<>();
        for (Object[] row : results) {
            AppealResponseDTO dto = new AppealResponseDTO();
            dto.setAppealNum((String) row[0]);
            Timestamp ts = (Timestamp) row[1];
            dto.setAppealDate(ts != null ? ts.toLocalDateTime() : null);
            dto.setGrvnNum((String) row[2]);
            dto.setInvestigationNum((String) row[3]);
            dto.setAppealContent((String) row[4]);
            appeals.add(dto);
        }
        return appeals;
    }

    // Get my appeals (employee)
    // employee sees own appeals
    public List<AppealResponseDTO> getMyAppeals() {
        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        StoredProcedureQuery sp = em.createStoredProcedureQuery("fetch_my_appeals");

        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);

        List<Object[]> results = sp.getResultList();
        List<AppealResponseDTO> appeals = new ArrayList<>();
        for (Object[] row : results) {
            AppealResponseDTO dto = new AppealResponseDTO();
            dto.setAppealNum((String) row[0]);
            Timestamp ts = (Timestamp) row[1];
            dto.setAppealDate(ts != null ? ts.toLocalDateTime() : null);
            dto.setGrvnNum((String) row[2]);
            dto.setInvestigationNum((String) row[3]);
            dto.setAppealContent((String) row[4]);
            appeals.add(dto);
        }
        return appeals;
    }
}
