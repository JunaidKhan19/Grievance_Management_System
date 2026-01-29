package com.gms.utils;

import com.gms.enums.GrievanceStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GrievanceStatusConverter
        implements AttributeConverter<GrievanceStatus, String> {

    @Override
    public String convertToDatabaseColumn(GrievanceStatus status) {
        return status == null ? null : status.getDbValue();
    }

    @Override
    public GrievanceStatus convertToEntityAttribute(String dbValue) {
        return dbValue == null ? null : GrievanceStatus.fromDbValue(dbValue);
    }
}