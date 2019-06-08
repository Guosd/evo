package com.github.framework.evo.base.advice;

import com.github.framework.evo.base.ApplicationEnvironment;
import com.github.framework.evo.common.Const;
import com.github.framework.evo.common.exception.BusinessException;
import com.github.framework.evo.common.uitl.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
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
public class ExceptionHandlerAdvice {
	@Autowired
	private ApplicationEnvironment applicationEnvironment;

	@ExceptionHandler(Exception.class)
	public ResponseEntity handle(Exception exception) {
		ResponseEntity responseEntity;
		if (exception instanceof HttpMessageNotReadableException) {
			log.warn("缺少请求体", exception);
			responseEntity = new ResponseEntity<>(HttpStatus.valueOf(Const.HTTP_STATUS_BAD_REQUEST));
		} else if (exception instanceof BindException) {
			BindException bindException = (BindException) exception;

			log.warn("参数绑定无效", exception);
			responseEntity = new ResponseEntity<>(toMessageList(bindException.getAllErrors()), HttpStatus.valueOf(Const.HTTP_STATUS_BAD_REQUEST));
		} else if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;

			log.warn("方法参数无效", exception);
			responseEntity = new ResponseEntity<>(toMessageList(methodArgumentNotValidException.getBindingResult().getAllErrors()), HttpStatus.valueOf(Const.HTTP_STATUS_BAD_REQUEST));
		} else if (exception instanceof BusinessException) {
			BusinessException businessException = (BusinessException) exception;

			String code = businessException.getCode();
			String message = businessException.getMessage();
			Object data = businessException.getData();

			Map<String, Object> map = new HashMap<>();
			map.put("code", code);
			map.put("message", message);
			map.put("data", data);

			log.error("业务异常 code={}, message={}, data={}", code, message, data == null ? null : JsonUtil.objectToJson(data), exception);
			responseEntity = new ResponseEntity<>(map, HttpStatus.valueOf(Const.HTTP_STATUS_INTERNAL_SERVER_ERROR));
		} else {
			log.error("服务异常", exception);

			Map<String, Object> map = new HashMap<>();
			if (applicationEnvironment.isExceptionEnabled()) {
				map.put("exception", toStackTrace(exception));
			}

			responseEntity = new ResponseEntity<>(map, HttpStatus.valueOf(Const.HTTP_STATUS_INTERNAL_SERVER_ERROR));
		}
		return responseEntity;
	}

	private List<String> toMessageList(List<ObjectError> objectErrorList) {
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

		return messageList;
	}

	private String toStackTrace(Throwable cause) {
		if (cause == null) {
			return null;
		}

		CharArrayWriter caw = new CharArrayWriter();
		cause.printStackTrace(new PrintWriter(caw));
		String stackTrace = caw.toString();
		caw.close();
		return stackTrace;
	}
}
