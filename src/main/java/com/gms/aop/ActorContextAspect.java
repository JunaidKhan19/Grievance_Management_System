package com.gms.aop;

import com.gms.utils.ActorContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActorContextAspect {

    // Pointcut for all grievance service methods
    @Pointcut("execution(* com.gms.services.GrievanceService.*(..))")
    public void grievanceServiceMethods() {}

    @Before("grievanceServiceMethods() && args(.., actorId, actorRole)")
    public void bindActorContext(JoinPoint joinPoint, String actorId, String actorRole) {
        // Only set if not already set to prevent recursion
        if (ActorContextHolder.getActorId() == null) {
            ActorContextHolder.setActorId(actorId);
            ActorContextHolder.setActorRole(actorRole);
        }
    }

    @After("grievanceServiceMethods()")
    public void clearActorContext(JoinPoint joinPoint) {
        ActorContextHolder.clear();
    }
}
