package com.java.basics.exception;

import com.java.basics.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class DepartmentExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleMethodArgumentNotvalidException(MethodArgumentNotValidException e)
    {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> errMsg = errors
                .stream()
                .map(ObjectError-> ObjectError.getDefaultMessage())
                .collect(Collectors.toList());
        Response responseDto = new Response("Exception Occured..!!",errMsg.toString());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DepartmentException.class)
    public ResponseEntity<Response> handleEmployeeException(DepartmentException e)
    {
        Response responseDto = new Response("Exception Occured..!!",e.getMessage());
        return new ResponseEntity<>(responseDto,HttpStatus.BAD_GATEWAY);
    }
}
