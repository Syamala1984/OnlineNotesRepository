package com.online.notepad.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.online.notepad.model.Notes;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class NoteFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Notes.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"NotEmpty.notes.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content",
				"NotEmpty.notes.content");

	}

}