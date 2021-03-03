package com.apilibrary.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.apilibrary.response.ErrorMessage;

import javassist.NotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorMessage> handle(MethodArgumentNotValidException exception) {
		List<ErrorMessage> dto = new ArrayList<>();

		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		fieldErros.forEach(e -> {
			String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorMessage error = new ErrorMessage(new GregorianCalendar(),
					exception.getFieldError().getDefaultMessage(), msg);
			dto.add(error);
		});

		return dto;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ErrorMessage handle(NotFoundException exception) {

		ErrorMessage error = new ErrorMessage(new GregorianCalendar(), exception.getMessage(), "Not found");

		return error;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ErrorMessage handle(SQLIntegrityConstraintViolationException exception) {

		ErrorMessage error = new ErrorMessage(new GregorianCalendar(), exception.getMessage(), "Id doesn't exist");

		return error;
	}

}
