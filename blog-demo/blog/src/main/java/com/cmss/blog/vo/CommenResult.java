package com.cmss.blog.vo;

import lombok.Data;

@Data
public class CommenResult {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    CommenResult(boolean success , int code , String msg , Object data){
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static CommenResult success(Object data){
        return new CommenResult(true,200,"success",data);
    }

    public static CommenResult fail(int code, String msg){
        return new CommenResult(false,code,msg,null);
    }
}
