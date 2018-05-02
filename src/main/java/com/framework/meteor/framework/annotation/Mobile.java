package com.framework.meteor.framework.annotation;

import com.framework.meteor.framework.validate.ValidateMobile;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 电话注解校验
 *
 * @author Meteor.wu
 * @since 2018/5/2 17:19
 */
@Target({ElementType.FIELD,  ElementType.PARAMETER })   // 约束注解应用的目标元素类型
@Retention(RUNTIME)   // 约束注解应用的时机
@Constraint(validatedBy ={ValidateMobile.class})  // 与约束注解关联的验证器
public @interface Mobile {
    String message() default "手机号码格式错误";
}
