package com.lzx.vo_filter.result;


public class ResultVo<T> {
    int code;
    String message;
    T data;
    int pager;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPager() {
        return pager;
    }

    public void setPager(int pager) {
        this.pager = pager;
    }

    public static void NotFound() {
        new ResultEntity(404).buildMessage("找不到页面");
    }

    public static <T> void OK(T data) {
        ResultEntity resultEntity = new ResultEntity(200);
        resultEntity.buildData(data);
    }

    public static ResultEntityBuilder buildCode(int code) {
        return new ResultEntity(code);
    }
}









