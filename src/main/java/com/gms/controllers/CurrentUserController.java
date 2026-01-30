package com.gms.controllers;

import com.gms.dto.CurrentUserDTO;
import com.gms.services.CurrentUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/current-user")
public class CurrentUserController {

    private final CurrentUserService currentUserService;

    public CurrentUserController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CurrentUserDTO> getCurrentUser() {
        return ResponseEntity.ok(currentUserService.getCurrentUser());
    }
}
