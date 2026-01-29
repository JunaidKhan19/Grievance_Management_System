package com.gms.services.impl;

import com.gms.dto.view.EmployeeByDepartmentView;
import com.gms.dto.view.OfficerBasicView;
import com.gms.dto.view.OfficerWorkloadView;
import com.gms.repositories.analytics.EmployeeAnalyticsRepository;
import com.gms.repositories.analytics.OfficerAnalyticsRepository;
import com.gms.services.AdminAnalyticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAnalyticsServiceImpl implements AdminAnalyticsService {

    private final EmployeeAnalyticsRepository employeeRepo;
    private final OfficerAnalyticsRepository officerRepo;

    public AdminAnalyticsServiceImpl(
            EmployeeAnalyticsRepository employeeRepo,
            OfficerAnalyticsRepository officerRepo
    ) {
        this.employeeRepo = employeeRepo;
        this.officerRepo = officerRepo;
    }

    @Override
    public List<EmployeeByDepartmentView> getEmployeeCountByDepartment() {
        return employeeRepo.countEmployeesByDepartment();
    }

    @Override
    public List<OfficerBasicView> getAllOfficers() {
        return officerRepo.findAllOfficersBasic();
    }

    @Override
    public List<OfficerWorkloadView> getOfficerWorkload() {
        return officerRepo.getOfficerWorkload();
    }
}
