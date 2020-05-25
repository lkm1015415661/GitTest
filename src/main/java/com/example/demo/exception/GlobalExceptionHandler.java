package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.result.CodeMsg;
import com.example.demo.result.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> handleException(HttpServletRequest request, Exception ex){
        ex.printStackTrace();

        if(ex instanceof GlobalException){
            GlobalException gex = (GlobalException)ex;
            return Result.error(gex.getCm());
        } else if(ex instanceof BindException){
            BindException bex = (BindException)ex;
            String message = bex.getAllErrors().get(0).getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}