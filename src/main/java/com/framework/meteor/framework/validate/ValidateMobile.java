package com.framework.meteor.framework.validate;

import com.framework.meteor.framework.annotation.Mobile;
import com.framework.meteor.framework.util.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验电话
 *
 * @author Meteor.wu
 * @since 2018/5/2 17:19
 */

public class ValidateMobile implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile mobile) {

    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        return ValidateUtil.isMobile(mobile);
    }
}
