package com.tp.common;

public class R<T> {
    /**
     * 状态码
     */
    private Integer code;
    private String msg;
    /**
     * 查询得到得结果数据
     */
    private T data;

    public R(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    public R(String msg){
        this.msg = msg;
    }

    public R(String msg, T data){
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
