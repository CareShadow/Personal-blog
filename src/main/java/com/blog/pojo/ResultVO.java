package com.blog.pojo;

import com.blog.constants.HttpStatusEnum;
import lombok.Getter;

/**
 * @ClassName ResultVO
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 18:07
 * @Version 1.0
 **/
@Getter
public class ResultVO<T> {
    private int code;
    private String msg;
    private T data;
    public ResultVO(HttpStatusEnum httpStatusEnum,T data){
        this.code = httpStatusEnum.getCode();
        this.msg = httpStatusEnum.getMessage();
        this.data = data;
    }
    public ResultVO(T data){
        this(HttpStatusEnum.SUCCESS,data);
    }
}
