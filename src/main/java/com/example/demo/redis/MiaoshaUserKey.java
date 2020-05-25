package com.example.demo.redis;

public class MiaoshaUserKey extends BasePrefix {
    public static final int Token_EXPIRE=3600*24*2;
    private MiaoshaUserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static MiaoshaUserKey token =new MiaoshaUserKey(Token_EXPIRE,"tk");




}
