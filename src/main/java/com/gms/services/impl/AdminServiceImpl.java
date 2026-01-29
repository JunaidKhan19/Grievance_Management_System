package com.gms.services.impl;

import com.gms.repositories.AdminProcedureRepository;
import com.gms.services.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminProcedureRepository adminProcedureRepo;

    public AdminServiceImpl(AdminProcedureRepository adminProcedureRepo) {
        this.adminProcedureRepo = adminProcedureRepo;
    }

    @Override
    public void deleteEmployee(String empnum, String actorId, String actorRole) {
        adminProcedureRepo.deleteEmployee(empnum, actorId, actorRole);
    }

    @Override
    public void deleteOfficer(String officernum, String actorId, String actorRole) {
        adminProcedureRepo.deleteOfficer(officernum, actorId, actorRole);
    }
}
