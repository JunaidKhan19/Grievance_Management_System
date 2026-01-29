package com.gms.controllers.analytics;

import com.gms.services.EmployeeAnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/analytics/employees")
@PreAuthorize("hasAnyRole('ADMIN','OFFICER')")
public class EmployeeAnalyticsController {

    private final EmployeeAnalyticsService service;

    public EmployeeAnalyticsController(EmployeeAnalyticsService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> listEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/by-department")
    public ResponseEntity<?> employeesByDepartment() {
        return ResponseEntity.ok(service.getEmployeeCountByDepartment());
    }
}
