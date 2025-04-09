package com.stanley.dodospring.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasAnyRole(T(com.stanley.dodospring.domain.UserRole).ROLE_ADMIN.toString())")
public @interface AdminRole {
}
