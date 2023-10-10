package com.springsecurity.practice.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.springsecurity.practice.config.ApplicationUserPermissions.*;

public enum ApplicationUserRoles {
    TEACHER(createTeacherPermissions()),
    STUDENT(createStudentPermissions()),

    ASSISTANT_TEACHER(new HashSet<>());

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }

    private static Set<ApplicationUserPermissions> createTeacherPermissions() {
        Set<ApplicationUserPermissions> permissions = new HashSet<>();
        permissions.add(ApplicationUserPermissions.TEACHER_READ);
        permissions.add(ApplicationUserPermissions.TEACHER_WRITE);
        return permissions;
    }

    private static Set<ApplicationUserPermissions> createStudentPermissions() {
        Set<ApplicationUserPermissions> permissions = new HashSet<>();
        permissions.add(ApplicationUserPermissions.STUDENT_READ);
        permissions.add(ApplicationUserPermissions.STUDENT_WRITE);
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities(){
        Set<GrantedAuthority> authorities =this.permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
