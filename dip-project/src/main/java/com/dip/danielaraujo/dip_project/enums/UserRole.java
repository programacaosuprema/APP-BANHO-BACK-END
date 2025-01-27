package com.dip.danielaraujo.dip_project.enums;

public enum UserRole {
    ADMIN("admin"),
    CLIENT("client"),
    OWNER("owner");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
