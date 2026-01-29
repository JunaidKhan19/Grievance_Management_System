package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/*
 * This is the Officer entity class
 */

@Entity
@Table(name = "officers")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "officerId")),
        @AttributeOverride(name = "userNum", column = @Column(name = "officernum", length = 4, columnDefinition = "CHAR(4)")),
        @AttributeOverride(name = "name", column = @Column(name = "officername", length = 30)),
        @AttributeOverride(name = "email", column = @Column(name = "officer_email", length = 100)),
        @AttributeOverride(name = "password", column = @Column(name = "officer_password"))
})
public class Officer extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ctgnum", referencedColumnName = "ctgNum", nullable = false, columnDefinition = "CHAR(4)")
    @JsonBackReference
    private Category category;

    @Column(name = "auth_key", length = 255, nullable = false)
    private String authKey;

    // Getters and Setters
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getAuthKey() { return authKey; }
    public void setAuthKey(String authKey) { this.authKey = authKey; }

    @Override
    public String toString() {
        return "Officer{" +
                "authKey='" + authKey + '\'' +
                '}';
    }
}



