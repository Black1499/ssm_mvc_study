package com.lzx.vo_filter.result;

public interface ResultEntityBuilder {
        //ResultEntityBuilder buildCode(int code);

        ResultEntityBuilder buildMessage(String message);

        <T> ResultEntityBuilder buildData(T data);

        ResultEntityBuilder buildPager(int page);

    }