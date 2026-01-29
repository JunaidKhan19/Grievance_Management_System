package com.gms.enums;

public enum Role {
    EMPLOYEE,
    OFFICER,
    ADMIN;

    /**
     * Spring Security requires roles to be prefixed with "ROLE_"
     */

    public String getSpringRole() {
        return "ROLE_" + this.name();
    }
}
