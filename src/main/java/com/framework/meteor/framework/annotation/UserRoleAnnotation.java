package com.framework.meteor.framework.annotation;


import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface UserRoleAnnotation {
//    UserTypeEnum[] roles();
}
