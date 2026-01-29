package com.gms.services;

import com.gms.dto.view.EmployeeBasicView;
import com.gms.dto.view.EmployeeByDepartmentView;

import java.util.List;

public interface EmployeeAnalyticsService {

    List<EmployeeBasicView> getAllEmployees();

    List<EmployeeByDepartmentView> getEmployeeCountByDepartment();
}
