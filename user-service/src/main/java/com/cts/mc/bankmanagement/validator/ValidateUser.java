package com.cts.mc.bankmanagement.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import com.cts.mc.bankmanagement.validator.ValidateUser.List;
@Documented
@Constraint(validatedBy = UserValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(List.class)
public @interface ValidateUser {
    String message() default "Invalid User or Password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    @Retention( RetentionPolicy.RUNTIME )
    @Target( { ElementType.TYPE } )
    @Documented
    @interface List {
    	ValidateUser[] value();
    }
    
}
