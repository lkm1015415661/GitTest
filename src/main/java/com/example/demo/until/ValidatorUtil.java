package com.example.demo.until;

import com.alibaba.druid.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatorUtil {

    private static Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return false;
        }

        Matcher matcher = MOBILE_PATTERN.matcher(mobile);
        return matcher.matches();
    }

//    public static void main(String[] args) {
//        boolean result1 = isMobile("13632481101");
//        boolean result2 = isMobile("1363248110");
//        System.out.println(result1);
//        System.out.println(result2);
//    }
}
