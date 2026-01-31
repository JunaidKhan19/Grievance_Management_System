package com.gms.repositories;

import com.gms.utils.ActorContextHolder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class GrievanceStatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void employeeIntendedResolve(String grvnnum) {

        StoredProcedureQuery query =
                entityManager.createStoredProcedureQuery("sp_employee_intended_resolve");

        query.registerStoredProcedureParameter("p_grvnnum", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_status", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_actor_id", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_actor_role", String.class, ParameterMode.IN);

        query.setParameter("p_grvnnum", grvnnum);
        query.setParameter("p_status", "intended_resolve");
        query.setParameter("p_actor_id", ActorContextHolder.getActorId());
        query.setParameter("p_actor_role", ActorContextHolder.getActorRole());

        query.execute();
    }
}
