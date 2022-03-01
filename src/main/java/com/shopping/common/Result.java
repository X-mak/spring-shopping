package com.shopping.common;

public class Result<T> {
    private String code;    //响应代码，400为错误，200为正确
    private String msg; //响应提示信息,若为搜索请求,则为页码总数
    private T data; //响应数据

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(String msg){
        Result result = new Result();
        result.setCode("200");
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> success(T data,String msg){
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.setCode("400");
        result.setMsg(msg);
        return result;
    }
}
