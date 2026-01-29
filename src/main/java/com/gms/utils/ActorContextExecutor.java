package com.gms.utils;

import com.gms.aop.SkipActorAspect;
import org.springframework.stereotype.Component;

@Component
public class ActorContextExecutor {

    @SkipActorAspect  // prevent recursion
    public void setActorContext(String actorId, String actorRole) {
        // Store actor info in ThreadLocal or wherever you use it
        ActorContextHolder.setActorId(actorId);
        ActorContextHolder.setActorRole(actorRole);
        System.out.println(">>> ACTOR CONTEXT SET: " + actorId + " / " + actorRole);
    }
}
