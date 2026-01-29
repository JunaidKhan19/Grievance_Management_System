package com.gms.dto.auth;

public class JwtUserDTO {

    private String userNum;
    private String role;

    public JwtUserDTO(String userNum, String role) {
        this.userNum = userNum;
        this.role = role;
    }

    public String getUserNum() {
        return userNum;
    }

    public String getRole() {
        return role;
    }
}
