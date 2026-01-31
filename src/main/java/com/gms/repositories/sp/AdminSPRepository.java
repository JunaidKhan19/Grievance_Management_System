package com.gms.repositories.sp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class AdminSPRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void deleteEmployee(String empnum, String actorId, String actorRole) {
        em.createNativeQuery("CALL sp_delete_employee(:e, :a, :r)")
                .setParameter("e", empnum)
                .setParameter("a", actorId)
                .setParameter("r", actorRole)
                .executeUpdate();
    }

    @Transactional
    public void deleteOfficer(String officernum, String actorId, String actorRole) {
        em.createNativeQuery("CALL sp_delete_officer(:o, :a, :r)")
                .setParameter("o", officernum)
                .setParameter("a", actorId)
                .setParameter("r", actorRole)
                .executeUpdate();
    }
}

