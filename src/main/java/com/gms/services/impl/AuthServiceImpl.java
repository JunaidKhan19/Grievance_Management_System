package com.gms.services.impl;

import com.gms.dto.auth.LoginRequestDTO;
import com.gms.dto.auth.LoginResponseDTO;
import com.gms.entities.Admin;
import com.gms.entities.Employee;
import com.gms.entities.Officer;
import com.gms.enums.Role;
import com.gms.exceptions.AuthKeyRequiredException;
import com.gms.exceptions.InvalidCredentialsException;
import com.gms.repositories.AdminRepository;
import com.gms.repositories.EmployeeRepository;
import com.gms.repositories.OfficerRepository;
import com.gms.security.JwtUtil;
import com.gms.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepo;
    private final OfficerRepository officerRepo;
    private final AdminRepository adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(EmployeeRepository employeeRepo,
                           OfficerRepository officerRepo,
                           AdminRepository adminRepo,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.employeeRepo = employeeRepo;
        this.officerRepo = officerRepo;
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO req) {

        String userNum = req.getUserNum();
        String rawPassword = req.getPassword();
        String authKey = req.getAuthKey();

        // ---------------------------
        // Find the user and determine role
        // ---------------------------
        Object userEntity = employeeRepo.findByUserNum(userNum);
        Role role = Role.EMPLOYEE;

        if (userEntity == null) {
            userEntity = officerRepo.findByUserNum(userNum);
            role = Role.OFFICER;
        }

        if (userEntity == null) {
            userEntity = adminRepo.findByUserNum(userNum);
            role = Role.ADMIN;
        }

        if (userEntity == null) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        // ---------------------------
        // Extract stored password & authKey
        // ---------------------------
        String storedPassword;
        String storedAuthKey = null;

        switch (role) {
            case EMPLOYEE -> storedPassword = ((Employee) userEntity).getPassword();
            case OFFICER -> {
                Officer officer = (Officer) userEntity;
                storedPassword = officer.getPassword();
                storedAuthKey = officer.getAuthKey();
            }
            case ADMIN -> {
                Admin admin = (Admin) userEntity;
                storedPassword = admin.getPassword();
                storedAuthKey = admin.getAuthKey();
            }
            default -> throw new IllegalStateException("Unexpected role: " + role);
        }

        // ---------------------------
        // Validate password
        // ---------------------------
        validatePassword(rawPassword, storedPassword);

        // ---------------------------
        // Validate authKey if required
        // ---------------------------
        if (role != Role.EMPLOYEE) {
            validateAuthKey(authKey, storedAuthKey, role);
        }

        // ---------------------------
        // Debug: Print actor info before generating token
        // ---------------------------
        System.out.println("✅ LOGIN SUCCESS");
        System.out.println("UserNum: " + userNum);
        System.out.println("ActorRole: " + role.name());

        // ---------------------------
        // Build JWT response
        // ---------------------------
        return buildResponse(userNum, role);
    }

    private void validatePassword(String rawPassword, String storedPassword) {
        if (!passwordEncoder.matches(rawPassword, storedPassword)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }

    private void validateAuthKey(String rawAuthKey, String storedAuthKey, Role role) {
        if (rawAuthKey == null || rawAuthKey.isBlank()) {
            throw new AuthKeyRequiredException(role.name() + " auth key required");
        }

        if (!passwordEncoder.matches(rawAuthKey, storedAuthKey)) {
            throw new InvalidCredentialsException("Invalid auth key");
        }
    }

    private LoginResponseDTO buildResponse(String userNum, Role role) {
        String token = jwtUtil.generateToken(userNum, role);  // pass enum directly
        return new LoginResponseDTO(token, role.name(), userNum);
    }
}
