package com.springsecurity.practice.config;

public enum ApplicationUserPermissions {
    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write"),
    TEACHER_READ("teacher_read"),
    TEACHER_WRITE("teacher_write");

    private final String permission;

    ApplicationUserPermissions(String permission){
        this.permission=permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
