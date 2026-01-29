package com.gms.services.impl;

import com.gms.dto.auth.RegisterRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder;

    public RegistrationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- COMMON VALIDATION ----------------

    private void validateBase(RegisterRequestDTO req) {
        if (req.getUserNum() == null ||
                req.getPassword() == null ||
                req.getEmail() == null ||
                req.getAddress() == null) {

            throw new RuntimeException("Invalid registration data");
        }
    }

    // ---------------- EMPLOYEE REGISTRATION ----------------

    @Transactional
    public void registerEmployee(RegisterRequestDTO req) {

        // ---------------- DEBUG CONSOLE ----------------
        System.out.println("=== Employee Registration Payload ===");
        System.out.println("userNum: " + req.getUserNum());
        System.out.println("name: " + req.getName());
        System.out.println("email: " + req.getEmail());
        System.out.println("department: " + req.getDepartment());
        System.out.println("role: " + req.getEmployeeRole());
        System.out.println("contactNum: " + req.getContactNum());
        System.out.println("address: " + req.getAddress());
        System.out.println("password: " + req.getPassword());
        System.out.println("====================================");

        validateBase(req);


        if (req.getContactNum() == null)
            throw new RuntimeException("Contact number is required for employee");

        em.createNativeQuery("CALL sp_create_employee(?, ?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, req.getUserNum())
                .setParameter(2, req.getName())
                .setParameter(3, req.getEmail())
                .setParameter(4, req.getDepartment() != null ? req.getDepartment() : "Staff")
                .setParameter(5, req.getEmployeeRole() != null ? req.getEmployeeRole() : "STAFF")
                .setParameter(6, req.getContactNum())
                .setParameter(7, req.getAddress() != null ? req.getAddress() : "NA")
                .setParameter(8, passwordEncoder.encode(req.getPassword()))
                .executeUpdate();
    }

    // ---------------- OFFICER REGISTRATION ----------------

    @Transactional
    public void registerOfficer(RegisterRequestDTO req) {

        validateBase(req);

        if (req.getCategoryNum() == null)
            throw new RuntimeException("Category number is required");

        if (req.getAuthKey() == null)
            throw new RuntimeException("Auth key is required");

        em.createNativeQuery("CALL sp_create_officer(?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, req.getUserNum())
                .setParameter(2, req.getName())
                .setParameter(3, req.getEmail())
                .setParameter(4, req.getCategoryNum())
                .setParameter(5, req.getAddress())
                .setParameter(6, passwordEncoder.encode(req.getPassword()))
                .setParameter(7, passwordEncoder.encode(req.getAuthKey()))
                .executeUpdate();
    }

    // ---------------- ADMIN REGISTRATION ----------------

    @Transactional
    public void registerAdmin(RegisterRequestDTO req) {

        validateBase(req);

        if (req.getContactNum() == null)
            throw new RuntimeException("Contact number is required");

        if (req.getAuthKey() == null)
            throw new RuntimeException("Auth key is required");

        em.createNativeQuery("CALL sp_create_admin(?, ?, ?, ?, ?, ?, ?)")
                .setParameter(1, req.getUserNum())
                .setParameter(2, req.getName())
                .setParameter(3, req.getEmail())
                .setParameter(4, req.getContactNum())
                .setParameter(5, req.getAddress())
                .setParameter(6, passwordEncoder.encode(req.getPassword()))
                .setParameter(7, passwordEncoder.encode(req.getAuthKey()))
                .executeUpdate();

    }
}
