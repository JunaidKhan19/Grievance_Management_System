package com.gms.repositories.sp;

import com.gms.dto.GrievanceLegalRefDTO;
import com.gms.utils.ActorContextHolder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class LegalRefSPRepository {

    @PersistenceContext
    private EntityManager em;

    public void assignLegalRefToGrievance(GrievanceLegalRefDTO dto) {
        String actorId = ActorContextHolder.getActorId();
        String actorRole = ActorContextHolder.getActorRole();

        StoredProcedureQuery sp = em.createStoredProcedureQuery("assign_legalrefs_to_grievance");

        sp.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter("p_legrefsnum", String.class, ParameterMode.IN);

        sp.setParameter("p_actor_id", actorId);
        sp.setParameter("p_actor_role", actorRole);
        sp.setParameter("p_grvnnum", dto.getGrvnNum());
        sp.setParameter("p_legrefsnum", dto.getLegrefsNum());

        sp.execute();
    }
}
