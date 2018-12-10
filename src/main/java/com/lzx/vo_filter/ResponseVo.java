package com.lzx.vo_filter;

public class ResponseVo<T> {

    int code;
    String message;
    T data;

    Throwable throwable;
    int page;

    public int getCode() {
        return code;
    }

    public ResponseVo setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseVo setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseVo setData(T data) {
        this.data = data;
        return this;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ResponseVo setThrowable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    public int getPage() {
        return page;
    }

    public ResponseVo setPage(int page) {
        this.page = page;
        return this;
    }
}
