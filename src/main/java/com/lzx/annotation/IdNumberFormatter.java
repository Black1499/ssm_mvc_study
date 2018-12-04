package com.lzx.annotation;

import com.lzx.dao.AreaCodeMapper;
import com.lzx.entity.AreaCode;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 本类是装换身份证号的详细实现
 * Formatter<T>可以注入一个对象，返回一个从身份证号提取出详细信息的对象
 */
@Component
public class IdNumberFormatter implements Formatter<String> {

    static Map<Integer,String> map=null;
    static {
        map=new HashMap<>();
        map.put(11,"北京市");
        map.put(12,"天津市");
        map.put(13,"河北省");
        map.put(14,"山西省");
        map.put(15,"内蒙古自治区");
        map.put(21,"辽宁省");
        map.put(22,"吉林省");
        map.put(23,"黑龙江省");
        map.put(31,"上海市");
        map.put(32,"江苏省");
        map.put(33,"浙江省");
        map.put(34,"安徽省");
        map.put(35,"福建省");
        map.put(36,"江西省");
        map.put(37,"山东省");
        map.put(41,"河南省");
        map.put(42,"湖北省");
        map.put(43,"湖南省");
        map.put(44,"广东省");
        map.put(45,"广西壮族自治区");
        map.put(46,"海南省");
        map.put(50,"重庆市");
        map.put(51,"四川省");
        map.put(52,"贵州省");
        map.put(53,"云南省");
        map.put(54,"西藏自治区");
        map.put(61,"陕西省");
        map.put(62,"甘肃省");
        map.put(63,"青海省");
        map.put(64,"宁夏回族自治区");
        map.put(65,"新疆维吾尔自治区");
        map.put(71,"台湾省");
        map.put(81,"香港特别行政区");
        map.put(82,"澳门特别行政区");
        map.put(91,"海外");
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

    public String getPlace(int code) {
        String place = map.get(code);
        return place;
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
            return "woman";
        } else {
            return "man";
        }
    }

    public String returnNewNum(String text) {
        if (Pattern.matches("^[0-9]{17}[0-9xX]{1}$", text)) {
            if (text.substring(17, 18) == "x") {
                text = text.substring(0, 17) + text.substring(17, 18).toUpperCase();
            }

            if (isIdNum(text)) {
                String place = getPlace(Integer.parseInt(text.substring(0, 2)));
                int age = getAge(text.substring(6, 14));
                String sex = getSex(text.substring(16, 17));
                return place+age + sex;
            }
        }
        return "";
    }

    @Override
    public String print(String object, Locale locale) {
        // 如果注入的是对象这里应该返回一个对象
        return returnNewNum(object);
    }

    @Override
    public String parse(String text, Locale locale) throws ParseException {
        // 如果注入的是对象这里应该返回一个对象
        String s = returnNewNum(text);
        return s;
    }

}
