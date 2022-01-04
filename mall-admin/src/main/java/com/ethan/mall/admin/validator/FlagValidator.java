package com.ethan.mall.admin.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author ethan
 * @Date 8:13 AM 2022/1/4
 * @Description 验证字段是否在指定范围内的注解
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {
    String[] values() default {};
    String message() default "flag is not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
