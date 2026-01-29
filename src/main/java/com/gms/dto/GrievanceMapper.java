package com.gms.dto;

import com.gms.enums.GrievanceStatus;
import com.gms.enums.Severity;

import java.time.LocalDateTime;
import java.sql.Timestamp;

public class GrievanceMapper {

    // vw_grievances_basic → GrievanceBasicDTO
    public static GrievanceBasicDTO toGrievanceBasicDTO(Object[] row) {
        GrievanceBasicDTO dto = new GrievanceBasicDTO();
        dto.setGrvnNum((String) row[0]);
        dto.setEmpNum((String) row[1]);
        dto.setEmpName((String) row[2]);
        dto.setCategoryNum((String) row[3]);
        dto.setCategoryName((String) row[4]);
        dto.setSubject((String) row[5]);
        dto.setStatus(GrievanceStatus.fromDbValue((String) row[6]));
        dto.setSeverity(Severity.fromDbValue((String) row[7]));
        dto.setDateFiled(row[8] != null ? ((Timestamp) row[8]).toLocalDateTime() : null);
        return dto;
    }

    // vw_grievance_with_employee / vw_resolved_grievances → GrievanceDTO
    public static GrievanceDTO toGrievanceDTO(Object[] row) {
        GrievanceDTO dto = new GrievanceDTO();
        dto.setGrvnNum((String) row[0]);
        dto.setSubject((String) row[1]);
        dto.setDescription(row.length > 2 ? (String) row[2] : null);

        if (row.length > 3 && row[3] != null)
            dto.setStatus(GrievanceStatus.fromDbValue((String) row[3]));

        if (row.length > 4 && row[4] != null)
            dto.setSeverity(Severity.fromDbValue((String) row[4]));

        if (row.length > 5 && row[5] != null) dto.setDateFiled(((Timestamp) row[5]).toLocalDateTime());

        if (row.length > 6) dto.setEmpNum((String) row[6]);
        if (row.length > 7) dto.setEmpName((String) row[7]);
        if (row.length > 8) dto.setEmpEmail((String) row[8]);
        if (row.length > 9) dto.setDepartment((String) row[9]);

        return dto;
    }
}
