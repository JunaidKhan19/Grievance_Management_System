package com.gms.services;

import com.gms.dto.view.EmployeeByDepartmentView;
import com.gms.dto.view.OfficerBasicView;
import com.gms.dto.view.OfficerWorkloadView;

import java.util.List;

public interface AdminAnalyticsService {

    List<EmployeeByDepartmentView> getEmployeeCountByDepartment();

    List<OfficerBasicView> getAllOfficers();

    List<OfficerWorkloadView> getOfficerWorkload();
}
