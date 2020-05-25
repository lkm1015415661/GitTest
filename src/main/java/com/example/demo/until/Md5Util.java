package com.example.demo.until;


import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    private static final String SALT = "1a2b3c4d";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    //第一次MD5加密:明文+salt的混合拼接
    public static String inputPassToFormPass(String inputPass){
        String src = "" + SALT.charAt(0) + SALT.charAt(2)+ inputPass + SALT.charAt(5)+ SALT.charAt(4);
        return md5(src);//如明文密码123456经过这个加密，被别人截获解读的结果会是12123456c3
    }
    //第二次MDS加密：用户输入密码+随机salt
    public static String formPassToDbPass(String formPass, String salt){
        String src = "" + salt.charAt(0) + salt.charAt(2)+ formPass + salt.charAt(5)+ salt.charAt(4);
        return md5(src);
    }
    //直接将用户密码转换成数据库里密码
    public static String inputPassToDbPass(String inputPass, String salt){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDbPass(formPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        String inputPass = "123456";
        String salt = "1a2b3c4d";
        String formPass = inputPassToFormPass(inputPass);
        String dbPass1 = formPassToDbPass(formPass, salt);
        String dbPass2 = inputPassToDbPass(inputPass, salt);
        System.out.println(formPass);
        System.out.println(dbPass1);
        System.out.println(dbPass2);

    }
}

