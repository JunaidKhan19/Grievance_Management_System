package com.gms.utils;

import com.gms.enums.Severity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SeverityConverter
        implements AttributeConverter<Severity, String> {

    @Override
    public String convertToDatabaseColumn(Severity severity) {
        return severity == null ? null : severity.getDbValue();
    }

    @Override
    public Severity convertToEntityAttribute(String dbValue) {
        return dbValue == null ? null : Severity.fromDbValue(dbValue);
    }
}
