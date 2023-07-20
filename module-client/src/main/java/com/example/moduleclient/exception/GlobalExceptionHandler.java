package com.example.moduleclient.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.moduleclient.constant.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public String handlerCustomException(CustomException e) {
		log.error("handlerCustomException : {}", e.getErrorCode());

		String scriptMessage = builderScript(e.getErrorCode().getMessage());

		return scriptMessage;
	}

	@ExceptionHandler(AccessDeniedException.class)
	public String handlerAccessDeniedException() {
		log.error("handlerAccessDeniedException : {}", ErrorCode.FORBIDDEN);

		String scriptMessage = builderScript(ErrorCode.FORBIDDEN.getMessage());

		return scriptMessage;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("handlerMethodArgumentNotValidException : {}", ErrorCode.BAD_REQUEST);

		String scriptMessage = builderScript(e.getBindingResult().getFieldError().getDefaultMessage());

		return scriptMessage;
	}

	private String builderScript(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + message + "');");
		sb.append("history.back();");
		sb.append("</script>");

		return sb.toString();
	}
}
