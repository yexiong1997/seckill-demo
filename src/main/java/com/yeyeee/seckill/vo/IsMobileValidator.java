package com.yeyeee.seckill.vo;

import com.yeyeee.seckill.utils.ValidatorUtil;
import com.yeyeee.seckill.validator.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required = false;

    @Override
    public void initialize (IsMobile constraintAnnotation) {
        boolean required = constraintAnnotation.required();


    }

    @Override
    public boolean isValid (String value, ConstraintValidatorContext context) {
        if (required){
            ValidatorUtil.isMobile(value);
        }else {
            if (StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }
        }

        return false;
    }
}
