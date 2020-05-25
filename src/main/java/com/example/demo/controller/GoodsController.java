package com.example.demo.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.domain.MiaoshaUser;
import com.example.demo.redis.RedisSevice;
import com.example.demo.service.MiaoshaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private MiaoshaUserService miaoshaUserService;
    @Autowired
    RedisSevice redisSevice;

    @RequestMapping("/to_list")
    public String toLogin(Model model,MiaoshaUser user){
        model.addAttribute("user", user);
        return "goods_list";

    }


}
