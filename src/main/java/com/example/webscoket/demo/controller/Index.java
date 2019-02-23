package com.example.webscoket.demo.controller;

import com.example.webscoket.demo.Cache.OnlineCache;
import com.example.webscoket.demo.entity.UserDo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "index";
    }

}
