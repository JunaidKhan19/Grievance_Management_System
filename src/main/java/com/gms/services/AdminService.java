package com.gms.services;

public interface AdminService {

    void deleteEmployee(String empnum, String actorId, String actorRole);

    void deleteOfficer(String officernum, String actorId, String actorRole);
}
