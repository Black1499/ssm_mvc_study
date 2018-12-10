package com.lzx.vo_filter.result;

public class ResultEntity implements ResultEntityBuilder {

        private ResultVo resultVo = new ResultVo();

        public ResultEntity (int code){
            resultVo.setCode(code);
        }
        public ResultEntity (){
        }

//        @Override
//        public ResultEntityBuilder buildCode(int code) {
//            resultVo.setCode(code);
//            return this;
//        }

        @Override
        public ResultEntityBuilder buildMessage(String message) {
            resultVo.setMessage(message);
            return this;
        }

        @Override
        public <T> ResultEntityBuilder buildData(T data) {
            resultVo.setData(data);
            return this;
        }


        @Override
        public ResultEntityBuilder buildPager(int page) {
            resultVo.setPager(page);
            return this;
        }


    }