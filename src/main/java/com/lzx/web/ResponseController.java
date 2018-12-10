package com.lzx.web;


import com.lzx.entity.AreaCode;
import com.lzx.entity.Book;
import com.lzx.vo_filter.ResponseVo;
import com.lzx.vo_filter.result.ResultVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {

    @ResponseBody
    @GetMapping("/response")
    public ResponseVo response() {

        ResultVo.OK(new Book());
        ResultVo.buildCode(200).buildMessage("我爱你啊呀").buildData("data");
        ResponseEntity.status(200).body(new Book());


        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(250).setMessage("我爱爱你")
                .setData(new AreaCode(1, "412827", "河南", "郑州", "评语", "阿发"));
        return responseVo;
    }

}
