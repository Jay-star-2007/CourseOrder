package com.scheduling.common.annotation;

import com.scheduling.entity.User.UserRole;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireRole {
    UserRole[] value();
} 