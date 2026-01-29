package com.gms.enums;

public enum Severity {

    LOW("low"),
    MEDIUM("medium"),
    HIGH("high"),
    CRITICAL("critical");

    private final String dbValue;

    Severity(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static Severity fromDbValue(String value) {
        for (Severity s : values()) {
            if (s.dbValue.equalsIgnoreCase(value)
                    || s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown severity: " + value);
    }
}
