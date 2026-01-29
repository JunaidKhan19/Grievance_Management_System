package com.gms.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/*
 * This is the Employee entity class
 */

@Entity
@Table(name = "employees")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "empId")),
        @AttributeOverride(name = "userNum", column = @Column(name = "empnum", length = 4, columnDefinition = "CHAR(4)")),
        @AttributeOverride(name = "name", column = @Column(name = "empname", length = 30)),
        @AttributeOverride(name = "email", column = @Column(name = "emp_email", length = 100)),
        @AttributeOverride(name = "password", column = @Column(name = "emp_password"))
})
public class Employee extends User {

    @Column(length = 30, nullable = false)
    private String department = "Staff";

    @Column(name = "contact_num", length = 10, unique = true, nullable = false)
    private String contactNum;

    @Column(name = "role", length = 20, nullable = false)
    private String employeeRole;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Grievance> grievances = new HashSet<>();

    // Getters and Setters
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getContactNum() { return contactNum; }
    public void setContactNum(String contactNum) { this.contactNum = contactNum; }

    public String getEmployeeRole() { return employeeRole; }
    public void setEmployeeRole(String employeeRole) { this.employeeRole = employeeRole; }

    public Set<Grievance> getGrievances() { return grievances; }
    public void setGrievances(Set<Grievance> grievances) { this.grievances = grievances; }

    public void addGrievance(Grievance grievance) {
        grievances.add(grievance);
        grievance.setEmployee(this);
    }

    public void removeGrievance(Grievance grievance) {
        grievances.remove(grievance);
        grievance.setEmployee(null);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department='" + department + '\'' +
                ", contactNum='" + contactNum + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                '}';
    }
}
