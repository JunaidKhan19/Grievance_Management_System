package com.gms.security;

import com.gms.enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {}

    /** Returns current actorId (userNum) from JWT */
    public static String getCurrentActorId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? (String) auth.getPrincipal() : null;
    }

    /** Returns role as String, e.g., "ROLE_ADMIN" */
    public static String getCurrentRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return null;

        return auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);
    }

    /** Returns role as Role enum, e.g., Role.ADMIN */
    public static Role getCurrentRoleEnum() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        String roleStr = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        if (roleStr == null || roleStr.equals("ROLE_ANONYMOUS")) {
            return null; // 🔥 critical line
        }

        return Role.valueOf(roleStr.replace("ROLE_", ""));
    }

}
