package com.smartFarm.was.web.exception;


import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.exception.custom.ExistedMemberException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;


@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalStateException.class)
    public ResultResponse<Void> forbiddenExHandle(IllegalStateException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.FORBIDDEN, ResultCode.FORBIDDEN.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccessDeniedException.class)
    public ResultResponse<Void> accessDeniedExHandle(AccessDeniedException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.DENIED.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultResponse<Void> illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.INVALID_PARAMETER.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse<Void> methodArgsNotNullExHandle(MethodArgumentNotValidException e) {
        log.error("[exceptionHandle] ex", e);
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.INVALID_PARAMETER.getCode(), errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResultResponse<Void> memberNotFoundExHandle(UsernameNotFoundException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.MEMBER_NOT_FOUND.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistedMemberException.class)
    public ResultResponse<Void> existedMemberExHandle(ExistedMemberException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.EXISTED_MEMBER.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public ResultResponse<Void> sqlExHandle(SQLException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.DB_ERROR.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResultResponse<Void> notFoundExHandle(NotFoundException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.NOT_FOUND, ResultCode.PAGE_NOT_FOUND.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResultResponse<Void> runTimeExHandle(RuntimeException e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultResponse<Void> exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ResultResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, ResultCode.INTERVAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
