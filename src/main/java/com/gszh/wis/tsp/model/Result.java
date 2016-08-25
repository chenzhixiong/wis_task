package com.gszh.wis.tsp.model;

public class Result {

    public static final Integer SUCCESS_CODE = 1;
    public static final Integer FAIL_CODE = 0;

    private Integer code; //1 成功  0 失败
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(Integer code) {
        this.code = code;
    }

    public void setSuccess() {
        this.code = 1;
    }

    public void setFail() {
        this.code = 0;
    }

    public Integer getCode() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}
