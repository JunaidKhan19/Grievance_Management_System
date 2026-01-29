package com.gms.controllers.analytics;

import com.gms.services.AdminAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/analytics")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAnalyticsController {

    private final AdminAnalyticsService service;

    public AdminAnalyticsController(AdminAnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/employees-by-department")
    public ResponseEntity<?> employeesByDepartment() {
        return ResponseEntity.ok(service.getEmployeeCountByDepartment());
    }

    @GetMapping("/officers-list")
    public ResponseEntity<?> listOfficers() {
        return ResponseEntity.ok(service.getAllOfficers());
    }

    @GetMapping("/officer-workload")
    public ResponseEntity<?> officerWorkload() {
        return ResponseEntity.ok(service.getOfficerWorkload());
    }
}
