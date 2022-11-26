package com.companyname.propertymanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler
{
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleFieldValidation(MethodArgumentNotValidException exception)
    {
        List<ErrorModel> errorModels=new ArrayList<>();
        ErrorModel errorModel=null;
       List<FieldError> fieldErrors=exception.getBindingResult().getFieldErrors();
       for(fieldErrors fe:errorModels)
       {

       }

    }*/
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex)
    {
        System.out.println("Business exception is thrown");
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
