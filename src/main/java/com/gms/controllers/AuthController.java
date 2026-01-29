package com.gms.controllers;

import com.gms.dto.auth.LoginRequestDTO;
import com.gms.dto.auth.LoginResponseDTO;
import com.gms.dto.auth.RegisterRequestDTO;
import com.gms.services.AuthService;
import com.gms.services.impl.RegistrationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    public AuthController(
            RegistrationService registrationService,
            AuthService authService
    ) {
        this.registrationService = registrationService;
        this.authService = authService;
    }

    // ===================== REGISTER =====================

    @PostMapping("/register/employee")
    public ResponseEntity<String> registerEmployee(
            @Valid @RequestBody RegisterRequestDTO req
    ) {
        registrationService.registerEmployee(req);
        return ResponseEntity.ok("Employee registered successfully");
    }

    @PostMapping("/register/officer")
    public ResponseEntity<String> registerOfficer(
            @Valid @RequestBody RegisterRequestDTO req
    ) {
        registrationService.registerOfficer(req);
        return ResponseEntity.ok("Officer registered successfully");
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(
            @Valid @RequestBody RegisterRequestDTO req
    ) {
        registrationService.registerAdmin(req);
        return ResponseEntity.ok("Admin registered successfully");
    }

    // ===================== LOGIN =====================

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO req,
            HttpServletResponse response
    ) {
        LoginResponseDTO dto = authService.login(req);

        ResponseCookie cookie = ResponseCookie.from("GMS_AUTH", dto.getAccessToken())
                .httpOnly(true)
                .secure(false)        // true in production (HTTPS)
                .path("/")
                .maxAge(Duration.ofHours(2))
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(dto); // optional: can return minimal body
    }


    // ===================== LOGOUT =====================

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            String actorId = auth.getName(); // userNum
            String actorRole = auth.getAuthorities()
                    .stream()
                    .findFirst()
                    .map(a -> a.getAuthority())
                    .orElse("UNKNOWN");

            System.out.println("LOGOUT :: user=" + actorId + ", role=" + actorRole);
        } else {
            System.out.println("LOGOUT :: anonymous request");
        }

        ResponseCookie cookie = ResponseCookie.from("GMS_AUTH", "")
                .httpOnly(true)
                .secure(false)   // true in prod
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok("Logged out successfully");
    }

}
