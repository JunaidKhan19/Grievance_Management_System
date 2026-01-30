package com.gms.aop;

import com.gms.utils.ActorContextHolder;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActorContextAspect {

    @Around("execution(* com.gms.services..*(..))")
    public Object setActorContext(ProceedingJoinPoint joinPoint) throws Throwable {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {

            String actorId = auth.getName(); // O005

            String actorRole = auth.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority(); // ROLE_OFFICER

            // 🔥 STRIP PREFIX
            if (actorRole.startsWith("ROLE_")) {
                actorRole = actorRole.substring(5);
            }

            ActorContextHolder.setActorId(actorId);
            ActorContextHolder.setActorRole(actorRole);
        }

        try {
            return joinPoint.proceed();
        } finally {
            ActorContextHolder.clear();
        }
    }
}
