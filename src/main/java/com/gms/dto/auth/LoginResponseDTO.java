package com.gms.dto.auth;

public class LoginResponseDTO {

    private String accessToken;
    private String tokenType = "Bearer";
    private String userNum;
    private String role;

    public LoginResponseDTO(String accessToken, String role, String userNum) {
        this.accessToken = accessToken;
        this.role = role;
        this.userNum = userNum;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUserNum() {
        return userNum;
    }

    public String getRole() {
        return role;
    }
}
