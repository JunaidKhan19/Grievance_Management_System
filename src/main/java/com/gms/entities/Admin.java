package com.gms.entities;

import jakarta.persistence.*;

/*
 * This is the Admin entity class
 */

@Entity
@Table(name = "admins")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "adminId")),
        @AttributeOverride(name = "userNum", column = @Column(name = "adminnum", length = 4, columnDefinition = "CHAR(4)")),
        @AttributeOverride(name = "name", column = @Column(name = "adminname", length = 30)),
        @AttributeOverride(name = "email", column = @Column(name = "admin_email", length = 100)),
        @AttributeOverride(name = "password", column = @Column(name = "admin_password"))
})
public class Admin extends User {

    @Column(name = "contact_num", length = 10, unique = true, nullable = false)
    private String contactNum;

    @Column(name = "auth_key", length = 255, nullable = false)
    private String authKey;

    // Getters and Setters
    public String getContactNum() { return contactNum; }
    public void setContactNum(String contactNum) { this.contactNum = contactNum; }

    public String getAuthKey() { return authKey; }
    public void setAuthKey(String authKey) { this.authKey = authKey; }
}
