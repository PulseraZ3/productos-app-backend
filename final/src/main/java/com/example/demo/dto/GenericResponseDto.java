package com.example.demo.dto;


public class GenericResponseDto<T> {
    
    private T response;
	private ErrorMessageDto error;
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	public ErrorMessageDto getError() {
		return error;
	}
	public void setError(ErrorMessageDto error) {
		this.error = error;
	}
}
