package com.example.demo.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.*;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public GenericResponseDto<String> 
	resourceNotFoundException(ResourceNotFoundException ex){
		ErrorMessageDto error = new ErrorMessageDto();
		error.setDate(LocalDate.now());
		error.setMessage(ex.getMessage());
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		GenericResponseDto<String> response = 
				new GenericResponseDto<>();
		response.setError(error);
		return response;
	}
}
