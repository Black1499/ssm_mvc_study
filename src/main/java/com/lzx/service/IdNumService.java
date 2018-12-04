package com.lzx.service;

import com.lzx.dao.AreaCodeMapper;
import com.lzx.entity.AreaCode;
import com.lzx.entity.People;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class IdNumService {

    String idNum = "41282719970105403X";


    @Autowired
    AreaCodeMapper areaCodeMapper;

    public People getInfoByIdNum(String value) {

        if (Pattern.matches("^[0-9]{11}[0-9xX]{1}$", value)) {
            if(value.substring(17, 18) == "x"){
                value = value.substring(0, 17)+value.substring(17, 18).toUpperCase();
            }

            if (isIdNum(value)){

            }
        }
        return null;
    }

    public boolean isIdNum(String id) {

        int[] w = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        char[] c = id.toCharArray();
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += (c[i] - '0') * w[i];
        }

        char[] verifyCode = "10X98765432".toCharArray();
        char ch = verifyCode[sum % 11];

        return c[17] == ch;

    }

    public String getPlace(String code) {
        AreaCode areaCode = areaCodeMapper.selectByCode(code);
        return areaCode.getDetail();
    }

    public int getAge(String date) {
        int old = Integer.parseInt(date.substring(0, 4));
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        int now = Integer.parseInt(df.format(new Date()));
        return now - old;
    }

    public String getSex(String nums) {
        int num = Integer.parseInt(nums);
        if (num % 2 == 0) {
            return "女";
        } else {
            return "男";
        }
    }

}
