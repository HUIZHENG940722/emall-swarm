package com.ethan.mall.admin.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ethan
 * @Date 9:37 PM 2022/1/4
 * @Description 状态标记校验器
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
    private String[] values;

    /**
     * 初始化方法
     * @param constraintAnnotation
     */
    @Override
    public void initialize(FlagValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.values = constraintAnnotation.values();
    }

    /**
     * 验证逻辑
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = false;
        if (value == null) {
            return true;
        }
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf(value))) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
