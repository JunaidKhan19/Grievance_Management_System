package com.gms.services.impl;

import com.gms.dto.CurrentUserDTO;
import com.gms.entities.Admin;
import com.gms.entities.Employee;
import com.gms.entities.Officer;
import com.gms.repositories.AdminRepository;
import com.gms.repositories.EmployeeRepository;
import com.gms.repositories.OfficerRepository;
import com.gms.services.CurrentUserService;
import com.gms.utils.ActorContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private final AdminRepository adminRepository;
    private final OfficerRepository officerRepository;
    private final EmployeeRepository employeeRepository;

    public CurrentUserServiceImpl(AdminRepository adminRepository,
                                  OfficerRepository officerRepository,
                                  EmployeeRepository employeeRepository) {
        this.adminRepository = adminRepository;
        this.officerRepository = officerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CurrentUserDTO getCurrentUser() {

        String actorId = ActorContextHolder.getActorId();
        String role = ActorContextHolder.getActorRole();

        if (actorId == null || role == null) {
            throw new RuntimeException("Actor context not found");
        }

        // Normalize role
        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        switch (role) {

            case "ADMIN":
                return mapAdmin(adminRepository.findByUserNum(actorId));

            case "OFFICER":
                return mapOfficer(officerRepository.findByUserNum(actorId));

            case "EMPLOYEE":
                return mapEmployee(employeeRepository.findByUserNum(actorId));

            default:
                throw new RuntimeException("Invalid actor role: " + role);
        }
    }

    /* ================= MAPPERS ================= */

    private CurrentUserDTO mapAdmin(Admin admin) {
        CurrentUserDTO dto = new CurrentUserDTO();
        dto.setUserNum(admin.getUserNum());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setActorRole("ADMIN");
        dto.setContactNum(admin.getContactNum());
        return dto;
    }

    private CurrentUserDTO mapOfficer(Officer officer) {
        CurrentUserDTO dto = new CurrentUserDTO();
        dto.setUserNum(officer.getUserNum());
        dto.setName(officer.getName());
        dto.setEmail(officer.getEmail());
        dto.setActorRole("OFFICER");
        if (officer.getCategory() != null) {
            dto.setCategory(officer.getCategory().getCtgName());
        }
        return dto;
    }

    private CurrentUserDTO mapEmployee(Employee employee) {
        CurrentUserDTO dto = new CurrentUserDTO();
        dto.setUserNum(employee.getUserNum());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setActorRole("EMPLOYEE");
        dto.setDepartment(employee.getDepartment());
        dto.setContactNum(employee.getContactNum());
        dto.setEmployeeRole(employee.getEmployeeRole());

        return dto;
    }
}
