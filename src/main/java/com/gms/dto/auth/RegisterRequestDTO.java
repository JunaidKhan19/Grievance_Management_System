package com.gms.dto.auth;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDTO {

    @NotBlank
    private String userNum;   // E001 / O001 / A001

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String address;

    // ---------- Employee only ----------
    private String department;
    private String contactNum;
    private String employeeRole; // WORKPLACE role (HR / CLERK / etc)

    // ---------- Officer only ----------
    private String categoryNum;

    // ---------- Officer & Admin ----------
    private String authKey;

    /* getters & setters */

    public String getUserNum() { return userNum; }
    public void setUserNum(String userNum) { this.userNum = userNum; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getContactNum() { return contactNum; }
    public void setContactNum(String contactNum) { this.contactNum = contactNum; }

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getEmployeeRole() { return employeeRole; }
    public void setEmployeeRole(String employeeRole) { this.employeeRole = employeeRole; }

    public String getCategoryNum() { return categoryNum; }
    public void setCategoryNum(String categoryNum) { this.categoryNum = categoryNum; }

    public String getAuthKey() { return authKey; }
    public void setAuthKey(String authKey) { this.authKey = authKey; }
}
