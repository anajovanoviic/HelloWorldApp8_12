package com.ana1.helloworld.security;

import com.ana1.helloworld.ApplicationUserPermission;
import com.google.common.collect.Sets;

import java.util.Set;
import static com.ana1.helloworld.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(TABLE_READ, TABLE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
