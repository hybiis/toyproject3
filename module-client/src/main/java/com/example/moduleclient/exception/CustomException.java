package com.example.moduleclient.exception;

import com.example.moduleclient.constant.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private final ErrorCode errorCode;
}
