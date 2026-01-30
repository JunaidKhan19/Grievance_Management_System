package com.gms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentUserDTO {

    private String userNum;
    private String name;
    private String email;
    private String actorRole;   // ADMIN / OFFICER / EMPLOYEE

    // Optional fields (role-based)
    private String employeeRole; // Employee
    private String department;   // Employee
    private String contactNum;   // Admin / Employee
    private String category;     // Officer

    /* ================= Getters & Setters ================= */

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActorRole() {
        return actorRole;
    }

    public void setActorRole(String actorRole) {
        this.actorRole = actorRole;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /* ================= Utility ================= */

    @Override
    public String toString() {
        return "CurrentUserDTO{" +
                "userNum='" + userNum + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + actorRole + '\'' +
                ", department='" + department + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
