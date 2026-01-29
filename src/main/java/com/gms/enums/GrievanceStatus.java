package com.gms.enums;

/**
 * Maps exactly to grievances.status in DB
 */
public enum GrievanceStatus {

    PENDING("pending"),
    IN_PROCESS("in-process"),
    INTENDED_RESOLVE("intended_resolve"),
    RESOLVED("resolved");

    private final String dbValue;

    GrievanceStatus(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    /**
     * Convert DB string → enum safely
     */
    public static GrievanceStatus fromDbValue(String value) {
        for (GrievanceStatus status : values()) {
            if (status.dbValue.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown grievance status: " + value);
    }
}
