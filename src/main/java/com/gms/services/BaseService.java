package com.gms.services;

import com.gms.utils.ActorContextExecutor;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService {

    @Autowired
    protected ActorContextExecutor actorContextExecutor;

    /**
     * Sets actor context for triggers/SPs.
     * Call this before any DB call that requires actor context.
     */
    protected void setActorContext(String actorId, String actorRole) {
        actorContextExecutor.setActorContext(actorId, actorRole);
    }
}
