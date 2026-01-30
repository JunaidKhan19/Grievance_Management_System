package com.gms.controllers;

import com.gms.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @DeleteMapping("/delete_employees/{empnum}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable String empnum, Authentication auth) {
        String actorId = auth.getName(); // From JWT
        String actorRole = "ADMIN";

        adminService.deleteEmployee(empnum, actorId, actorRole);
        return ResponseEntity.ok(Map.of("message", "Employee deleted"));
    }

    @DeleteMapping("/delete_officers/{officernum}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOfficer(
            @PathVariable String officernum,
            Authentication auth
    ) {
        String actorId = auth.getName();
        String actorRole = "ADMIN";

        adminService.deleteOfficer(officernum, actorId, actorRole);

        return ResponseEntity.ok(Map.of("message", "Officer deleted"));
    }

}
