package com.example.webscoket.demo.controller;

import com.example.webscoket.demo.Cache.OnlineCache;
import com.example.webscoket.demo.entity.UserDo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登陆、首页等功能
 * @Author: DING WEI
 * @Date: 2019-03-24 16:44ß
 * @Version: 1.0
 */
@Controller
public class Index {

    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public boolean login(UserDo userDo){
        if(userDo.getPassword().equals("1")){
            OnlineCache.online.add(userDo);
            return true;
        }
        return false;
    }

    @RequestMapping("/index")
    public String index1(Model model,String userName){
        model.addAttribute("userName",userName);
        System.out.println(userName);
        return "index";
    }

}
