package com.ritoinfo.framework.evo.sp.base.advice;

import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-03-05 20:39
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandlerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse> handle(Exception exception) {
		ServiceResponse serviceResponse;
		if (exception instanceof BindException) {
			BindException bindException = (BindException) exception;

			List<ObjectError> objectErrorList = bindException.getAllErrors();
			List<String> messageList = new ArrayList<>();
			for (ObjectError objectError : objectErrorList) {
				String message;
				if (objectError instanceof FieldError) {
					FieldError fieldError = (FieldError) objectError;
					message = fieldError.getField() + fieldError.getDefaultMessage();
				} else {
					message = objectError.getDefaultMessage();
				}
				messageList.add(message);
			}

			serviceResponse = ServiceResponse.badRequest(messageList);
		} else if (exception instanceof RestException) {
			RestException restException = (RestException) exception;

			Map<String, Object> map = new HashMap<>();
			map.put("errorCode", restException.getCode());
			map.put("errorMessage", restException.getMessage());
			map.put("errorData", restException.getData());

			serviceResponse = ServiceResponse.internalServerError(map);
		} else {
			log.error("不期望的异常", exception);

			serviceResponse = ServiceResponse.internalServerError();
		}
		return new ResponseEntity<>(serviceResponse, HttpStatus.valueOf(Integer.parseInt(serviceResponse.getCode())));
	}
}
