package com.grosup.ttzy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.SessionBean;
import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.service.SessionService;
import com.grosup.ttzy.service.UserService;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.Constant;
import com.grosup.ttzy.util.HttpRequest;
import com.grosup.ttzy.util.ObjectUtil;
import com.grosup.ttzy.util.StringUtil;

@Controller
@RequestMapping("/wx")
public class WxLoginController {

    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private UserService userService;

    private static Logger LOGGER = Logger.getLogger(WxLoginController.class);

    /**
     * 小程序授权登录
     * 
     * @param request
     * @param code
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "login.do")
    @ResponseBody
    public Map<String, Object> wxLogin(HttpServletRequest request, @RequestParam String code) {
        LOGGER.info("Begin weixin login...");
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断code是否合法
        if (StringUtil.isNullOrEmpty(code)) {
            map.put("code", CodeUtil.SUCCESS);
            map.put("msg", "code 不合法");
            return map;
        }
        // 微信小程序appId
        String wxspAppid = "wx35edb0ab6b0f2dfb";
        // 微信 app secret (小程序秘钥)
        String wxspSecret = "acbef93825431b17ab51b3bb324c00e4";
        // 用户授权
        String grant_type = "authorization_code";

        // 请求微信接口 用code 获取 session_key 和 openid
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret
                + "&js_code=" + code + "&grant_type=" + grant_type;
        LOGGER.info("params is:" + params);
        // 发送请求
        String sr = HttpRequest.sendGet(Constant.JSCODE2_URL, params);
        // 转换为json
        JSONObject json = JSONObject.fromObject(sr);
        LOGGER.info("请求微信接口返回的信息为" + json);
        if(ObjectUtil.isNotNull(json.get("errcode"))) {
            map.put("code", CodeUtil.SUCCESS);
            map.put("msg", "code 不合法");
            return map;
        }
        // 会话密钥
        String session_key = json.get("session_key").toString();
        // 用户唯一标识
        String openid = (String) json.get("openid");
        LOGGER.info("用户的opinID为-----------------"+ openid);
        // 微信用户在开放平台的唯一标识符
         String unionid = (String) json.get("unionId");
         LOGGER.info("用户的unionid为-----------------"+ unionid);
         try {
             String third_session = session.getId();
             map.put("third_session", third_session);
             SessionBean sessionBean = new SessionBean(third_session, session_key, openid);
             if (sessionService.checkThirdSession(third_session)) {
                 //更新third_session
                 sessionService.updateThirdSession(sessionBean);
             } else {
                 //将session保存数据库
                 sessionService.insertSessionValue(sessionBean);
             }
//           ***根据unionId去数据库查询用户是否注册，如果未注册，session返回用户标识未注册
//           并返回用户注册状态----未注册/注册还未通过审核，如果已经注册并通过审核，返回用户信息并写入session
             Map<String, Object> userParam = new HashMap<String, Object>();
             userParam.put("openId", openid);
//             userParam.put("openId", "oVEQd0W7jgUobhqDk-Q8IINdoiCY");
             UserBean userBean = userService.getUserInfo(userParam);
             if (userBean == null) {
                 //用户未注册
                 map.put("status", -1);
             } else {
                 map.put("status", userBean.getStatus());
                 map.put("userInfo", JSONObject.fromObject(userBean));
                 session.setAttribute("userId", userBean.getUid());
             }
             map.put("code", CodeUtil.SUCCESS);
             map.put("msg", "校验登录成功");
     } catch (Exception e) {
         LOGGER.error("校验登录失败", e);
     }
        return map;
    }
}
