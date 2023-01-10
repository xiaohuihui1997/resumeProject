package com.wjz.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Result<T>  {

    //泛型数据  任何类型数据均可以被包含
    private T data;
    //告诉前端返回数据成功与否
    private int code;
    //保留数据错误信息
    private String msg;
    //数据总数 用于分页
    private long total;

    public Result(T data) {
        this.data = data;
    }

    //成功，返回自定义信息和默认200的code值
    public static Result sucess(String msg){
        Result result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    //成功，返回自定义数据，和默认的200code以及“成功”提示
    public static <T> Result<T> sucess(T data){
        Result<T> result = new Result<>(data);
        result.setCode(200);
        result.setMsg("操作成功!");
        return result;
    }

    //成功，返回两个自定义值
    public static <T> Result<T> sucess(T data,String msg){
        Result<T> result = new Result<>(data);
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    //成功，有总数返回的，一般用于分页
    public static <T> Result<T> sucess(T data,String msg,long total){
        Result<T> result = new Result<>(data);
        result.setCode(200);
        result.setTotal(total);
        result.setMsg(msg);
        return result;
    }

    //失败，只返回自定义提示信息和默认的500code值
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static Result paramCheckError(String msg){
        Result result = new Result();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

}
