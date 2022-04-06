package com.blog.config;

import com.blog.pojo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionControllerAdvice
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 19:04
 * @Version 1.0
 **/
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public ResultVO<String> NullPointerExceptionHandler(NullPointerException e){
        return new ResultVO<>(e.getMessage());
    }
}
