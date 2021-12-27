package com.cheese.admin.advice;

import com.cheese.core.exception.CheeseCode;
import com.cheese.core.exception.CustomException;
import com.cheese.admin.exception.InvalidParameterException;
import com.cheese.admin.model.response.CustomErrorResponse;
import com.cheese.admin.model.response.ErrorResponse;
import com.cheese.core.model.response.CheeseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     *   @Valid 검증 실패 시 Catch
     */
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<CustomErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        log.error("handleInvalidParameterException", e);
        CheeseCode cheeseCode = e.getErrorCode();

        final CustomErrorResponse response = CustomErrorResponse
                .builder()
                .status(cheeseCode.getStatus())
                .code(cheeseCode.getCode())
                .errors(e.getErrors());

        return new ResponseEntity<>(response, HttpStatus.resolve(cheeseCode.getStatus()));
    }

    /**
     *  CustomException을 상속받은 클래스가 예외를 발생 시킬 시, Catch하여 ErrorResponse를 반환한다.
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("handleCustomException", e);
        CheeseCode cheeseCode = e.getErrorCode();

        ErrorResponse response = ErrorResponse
                .builder()
                .status(cheeseCode.getStatus())
                .code(cheeseCode.getCode())
                .message(cheeseCode.getMessage());

        return new ResponseEntity<>(response, HttpStatus.resolve(cheeseCode.getStatus()));
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);

        ErrorResponse response = ErrorResponse
                .builder()
                .code(CheeseCode.INTERNAL_SERVER_ERROR.getCode());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}