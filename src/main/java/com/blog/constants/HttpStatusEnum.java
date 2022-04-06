package com.blog.constants;

import lombok.Getter;

@Getter
public enum HttpStatusEnum {
    SUCCESS(200,"操作成功"),
    FAILED(201,"响应失败"),
    VALIDATE_FAILED(1000,"参数校验失败"),
    ERROR(500,"位置错误");
    private int code;
    private String message;
    HttpStatusEnum(int code,String message){
        this.code = code;
        this.message = message;
    }
}
