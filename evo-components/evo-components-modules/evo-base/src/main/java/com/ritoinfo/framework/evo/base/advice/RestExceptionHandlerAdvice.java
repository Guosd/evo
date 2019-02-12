package com.ritoinfo.framework.evo.base.advice;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.exception.BizzException;
import com.ritoinfo.framework.evo.common.exception.RestException;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-05 20:39
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandlerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity handle(Exception exception) {
		ResponseEntity responseEntity;
		if (exception instanceof BindException) {
			BindException bindException = (BindException) exception;

			log.warn("参数绑定无效", exception);
			responseEntity = new ResponseEntity<>(ServiceResponse.of(Const.RC_FAIL_REQUEST_PARAM, Const.getRcm(Const.RC_FAIL_REQUEST_PARAM), toMessageList(bindException.getAllErrors())), HttpStatus.valueOf(Const.HTTP_STATUS_BAD_REQUEST));
		} else if (exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;

			log.warn("方法参数无效", exception);
			responseEntity = new ResponseEntity<>(ServiceResponse.of(Const.RC_FAIL_REQUEST_PARAM, Const.getRcm(Const.RC_FAIL_REQUEST_PARAM), toMessageList(methodArgumentNotValidException.getBindingResult().getAllErrors())), HttpStatus.valueOf(Const.HTTP_STATUS_BAD_REQUEST));
		} else if (exception instanceof RestException) {
			RestException restException = (RestException) exception;

			String code = restException.getCode();
			String message = exception.getMessage();
			message = StringUtil.isBlank(message) ? Const.getRcm(code) : message;// TODO 国际化替换Const

			log.warn("REST 异常: " + code + " " + message + " " + restException.getCause(), exception);
			responseEntity = new ResponseEntity<>(ServiceResponse.of(code, message, restException.getData()), HttpStatus.valueOf(Const.HTTP_STATUS_OK));
		} else if (exception instanceof BizzException) {
			BizzException bizzException = (BizzException) exception;

			log.error("未按要求转换业务异常", exception);
			responseEntity = new ResponseEntity<>(ServiceResponse.of(Const.RC_BASE_EXCEPTION, Const.getRcm(Const.RC_BASE_EXCEPTION), bizzException.getData()), HttpStatus.valueOf(Const.HTTP_STATUS_INTERNAL_SERVER_ERROR));
		} else {
			log.error("不期望的内部服务异常", exception);
			responseEntity = new ResponseEntity<>(ServiceResponse.of(Const.RC_FAIL_UNEXPECT, Const.getRcm(Const.RC_FAIL_UNEXPECT)), HttpStatus.valueOf(Const.HTTP_STATUS_INTERNAL_SERVER_ERROR));
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
}
