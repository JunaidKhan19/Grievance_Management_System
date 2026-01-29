package com.gms.security;

import com.gms.enums.Role;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Skip auth endpoints
        String path = request.getServletPath();
        if (path.startsWith("/auth/register") || path.startsWith("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        // If already authenticated, skip
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractTokenFromCookie(request);

        if (token != null && jwtUtil.validateToken(token)) {

            Claims claims = jwtUtil.getClaims(token);
            String actorId = claims.getSubject();
            String roleStr = claims.get("actorRole", String.class);

            try {
                Role role = Role.valueOf(roleStr);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                actorId,
                                null,
                                List.of(new SimpleGrantedAuthority(role.getSpringRole()))
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (IllegalArgumentException ignored) {
                // Invalid role — do not authenticate
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("GMS_AUTH".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
