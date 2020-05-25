package com.example.demo.service;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.dao.MiaoshaUserDao;
import com.example.demo.domain.MiaoshaUser;

import com.example.demo.exception.GlobalException;
import com.example.demo.redis.MiaoshaUserKey;
import com.example.demo.redis.RedisSevice;
import com.example.demo.result.CodeMsg;
import com.example.demo.until.Md5Util;
import com.example.demo.until.UUIDUtil;
import com.example.demo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {


    public static final String COOKIE_TOKEN_NAME="token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisSevice redisSevice;
    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response,LoginVo loginVo) {
        if(loginVo==null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String moblie=loginVo.getMobile();
        String formPass=loginVo.getPassword();
        //判断手机号是否为空
        MiaoshaUser user=getById(Long.parseLong(moblie));
        if(user==null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);

        }
        //验证密码
        String salt = user.getSalt();
        String dbPass = user.getPassword();
        String md5Pass = Md5Util.formPassToDbPass(formPass, salt);
        System.out.println(salt);
        System.out.println(dbPass);
        System.out.println(md5Pass);
        if(!dbPass.equals(md5Pass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        redisSevice.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSecconds());
        cookie.setPath("/");

        response.addCookie(cookie);
        return true;

    }

    public MiaoshaUser getByToke(String token,HttpServletResponse response) {
        if(StringUtils.isEmpty(token)){
            return null;
        }

        //延长有效期
        MiaoshaUser user = redisSevice.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        if(user != null){
            addCookie(token, response, user);
        }
        return user;
    }

    private void addCookie(String token, HttpServletResponse response, MiaoshaUser user){
        redisSevice.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSecconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }


}
