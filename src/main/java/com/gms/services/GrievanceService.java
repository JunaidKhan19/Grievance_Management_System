package com.gms.services;

import com.gms.dto.*;

import java.util.List;

public interface GrievanceService {

    // 1. File a grievance → calls SP
    public GrievanceResponseDTO fileGrievance(GrievanceRequestDTO request, String actorId, String actorRole);

    // 2. Get grievance by number
    GrievanceDTO getGrievancesByNum(String grvnNum);

    GrievanceDTO getGrievanceByNum(String grvnNum);

    // 3. Get all grievance by either status or empnum or both
    List<GrievanceBasicDTO> getAllGrievances(String empNum, String status);

    // 5. Count by status (dashboard)
    List<Object[]> countByStatus();

    // 6. Count by category (dashboard)
    List<Object[]> countByCategory();

    // 7. List grievances by category
    List<GrievanceCategoryDTO> listByCategory(String category);

    // 8. Assign grievance → SP (admin)
    String assignGrievance(GrievanceAssignDTO assignDTO);

    // 9. Resolve grievance → SP (officer)
    String resolveGrievance(GrievanceResolveDTO resolveDTO);

    // 10. Delete grievance → SP (admin)
    void deleteGrievance(String grvnNum, String actorId, String actorRole);

    // 11. Check existence
    boolean exists(String grvnNum);

    // 12. update status to intended_resolve
    void employeeIntendedResolve(String grvnnum);

    ResolutionDTO getResolutionByGrievance(String grvnNum);
}
