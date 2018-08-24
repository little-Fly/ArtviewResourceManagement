package com.grosup.ttzy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.GET,value = "wxLogin")
    @ResponseBody
    public void weixinLogin(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
