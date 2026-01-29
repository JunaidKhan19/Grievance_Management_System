package com.gms.utils;

public class ActorContextHolder {

    private static final ThreadLocal<String> actorId = new ThreadLocal<>();
    private static final ThreadLocal<String> actorRole = new ThreadLocal<>();

    // Setters
    public static void setActorId(String id) {
        actorId.set(id);
    }

    public static void setActorRole(String role) {
        actorRole.set(role);
    }

    // Getters
    public static String getActorId() {
        return actorId.get();
    }

    public static String getActorRole() {
        return actorRole.get();
    }

    // Clear (important to prevent memory leaks)
    public static void clear() {
        actorId.remove();
        actorRole.remove();
    }
}
