package com.blog.exception;

/**
 * @ClassName NullLoginException
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 19:01
 * @Version 1.0
 **/
public class NullLoginException extends Exception{
    private int code;
    private String msg;

    public NullLoginException(){
        this(1001,"接口错误");
    }
    public NullLoginException(String msg){
        this(201,msg);
    }

    public NullLoginException(int code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
