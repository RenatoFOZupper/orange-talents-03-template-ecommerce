package br.com.zup.mercadolivre.compartilhado;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {

	private final MessageSource messageSource;

	public ValidationErrorHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationErrorOutputDTO handleValidationError(MethodArgumentNotValidException exception) {
		
		List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		return buildValidationErrors(globalErrors, fieldErrors);
	}

	private ValidationErrorOutputDTO buildValidationErrors(List<ObjectError> globalErrors,
			List<FieldError> fieldErrors) {
		
		ValidationErrorOutputDTO validationErrors = new ValidationErrorOutputDTO();
		
		globalErrors.forEach(error -> validationErrors.addError(getErrorMessage(error)));
		fieldErrors.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			validationErrors.addFieldError(error.getField(), errorMessage);
		});
		
		
		return validationErrors;
	}
	
	public String getErrorMessage(ObjectError objectError) {
		return messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
	}
	
	
}
