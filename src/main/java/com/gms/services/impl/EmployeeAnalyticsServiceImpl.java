package com.gms.services.impl;

import com.gms.dto.view.EmployeeBasicView;
import com.gms.dto.view.EmployeeByDepartmentView;
import com.gms.repositories.analytics.EmployeeAnalyticsRepository;
import com.gms.services.EmployeeAnalyticsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeAnalyticsServiceImpl implements EmployeeAnalyticsService {

    private final EmployeeAnalyticsRepository repository;

    public EmployeeAnalyticsServiceImpl(EmployeeAnalyticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmployeeBasicView> getAllEmployees() {
        return repository.findAllEmployeesBasic();
    }

    @Override
    public List<EmployeeByDepartmentView> getEmployeeCountByDepartment() {
        return repository.countEmployeesByDepartment();
    }
}
