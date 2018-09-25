package com.grosup.ttzy.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.util.CookieUtil;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.HttpRequest;
import com.grosup.ttzy.util.StringUtil;

@Controller
@RequestMapping("/login")
public class PcLoginController {
    
    private Logger logger = Logger.getLogger(PcLoginController.class);
    
    /**
     * 小程序授权登录
     * 
     * @param request
     * @param code
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "pcLogin")
    @ResponseBody
    public void pcLogin(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String code) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断code是否合法
        if (StringUtil.isNullOrEmpty(code)) {
            map.put("status", 0);
            map.put("msg", "code 不合法");
            try {
                response.sendRedirect("loginurl");
                return;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        // appid
        String appid = "wxb41c2d9e3682c984";
        // 微信 app secret (秘钥)
        String appSecret = "89742aa2e8e08bfa83fa6f8789621c02";
        // 用户授权
        String grant_type = "authorization_code";
        
     // 获取微信access_token
        String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        
        String params = "appid="
                + appid
                + "&secret="
                + appSecret
                + "&code="
                + code
                + "&grant_type=" + grant_type;
        String access_token_return_str = HttpRequest.sendGet(access_token_url, params);
        
        // 转换为json
        JSONObject access_token_return_json = JSONObject.fromObject(access_token_return_str);
        // 用户唯一标识
        String openid = (String) access_token_return_json.get("openid");
        String access_token = (String) access_token_return_json
                .get("access_token");
        openid = (String) access_token_return_json.get("openid");
        String refreshToken = (String) access_token_return_json
                .get("refresh_token");
        
        // 通过access_token和openid获取微信用户信息
        String userInfo_url = "https://api.weixin.qq.com/sns/userinfo";
        String weixin_userInfo_Str = HttpRequest.sendGet(userInfo_url, "access_token="
                + access_token + "&openid=" + openid);
        JSONObject weixin_userInfo_json = JSONObject.fromObject(weixin_userInfo_Str);
        String unionid = (String) weixin_userInfo_json.get("unionid");
        String nickname = (String) weixin_userInfo_json.get("nickname");
        String headimgurl = (String) weixin_userInfo_json
                .getString("headimgurl");
        
     // 通过微信unionid查询用户信息
//        UserBean user = userWS.getUserbeanbyWeixinUnionid(unionid);
        UserBean user = new UserBean();
        user.setUid(1L);
        user.setOpenId("1111");
        user.setNickName("薛利飞");
        user.setCreateTime(new Date());
        user.setLastTime(new Date());
        //查询用户信息
        
        user.setLastValidTime(System.currentTimeMillis());
        CookieUtil.setCookies(response, user);
        try {
            response.sendRedirect("helloword.do");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return;
    }
}
