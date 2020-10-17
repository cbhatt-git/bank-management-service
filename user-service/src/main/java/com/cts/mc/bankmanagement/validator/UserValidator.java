package com.cts.mc.bankmanagement.validator;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cts.mc.bankmanagement.dto.UserDto;
import com.cts.mc.bankmanagement.repositories.UserRepository;
import com.cts.mc.bankmanagement.validator.groups.Create;
import com.cts.mc.bankmanagement.validator.groups.Update;

public class UserValidator implements ConstraintValidator<ValidateUser, UserDto> {

	private static final Logger log = LoggerFactory.getLogger(UserValidator.class);
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean isValid(UserDto user, ConstraintValidatorContext context) {
		
		
		context.disableDefaultConstraintViolation();
		boolean isValid = false;

		// Get active group here
		Set<Class<?>> activeGroups = null;

		if (context instanceof ConstraintValidatorContextImpl) {
			activeGroups = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getGroups();
		} else {
			activeGroups = Collections.emptySet();
		}

		if (activeGroups.contains(Create.class)) {
			log.info("Validating user details before creating ...");
			isValid = !userRepo.existsByUsername(user.getUsername());

			if(!isValid) {
				log.info("Failed to validate user, username already exists ...");
				context.buildConstraintViolationWithTemplate("Username already exists!").addConstraintViolation();
			}
		} else if (activeGroups.contains(Update.class)) {
			log.info("Validating user details before updating ...");
			isValid = true;
		}
		return isValid;
	}

}
