package com.grid.dynamics.demoprojecthospital.models.enums;

public enum UserRole{
    PATIENT("PATIENT"),
    DOCTOR("DOCTOR"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}