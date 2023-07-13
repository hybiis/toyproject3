package com.example.modulecore.util;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ApiUtil {
	public static <T> ApiResult<T> success(T response) {
		return new ApiResult<>(true, response, null);
	}

	public static ApiResult<?> error(String message, HttpStatus httpStatus) {
		return new ApiResult<>(false, null, new ApiError(message, httpStatus.value()));
	}

	@Getter
	@RequiredArgsConstructor
	public static class ApiResult<T> {
		private final boolean success;
		private final T response;
		private final ApiError error;
	}

	@Getter
	@RequiredArgsConstructor
	public static class ApiError {
		private final String message;
		private final int status;
	}
}
