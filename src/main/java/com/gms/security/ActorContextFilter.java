package com.gms.security;

import com.gms.utils.ActorContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ActorContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {

            String actorId = auth.getName(); // E080
            String actorRole = auth.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority(); // ROLE_EMPLOYEE

            if (actorRole.startsWith("ROLE_")) {
                actorRole = actorRole.substring(5);
            }

            ActorContextHolder.setActorId(actorId);
            ActorContextHolder.setActorRole(actorRole);
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            ActorContextHolder.clear();
        }
    }
}
