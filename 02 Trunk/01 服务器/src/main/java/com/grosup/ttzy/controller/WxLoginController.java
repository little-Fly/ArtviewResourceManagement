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
import com.grosup.ttzy.util.HttpRequest;
import com.grosup.ttzy.util.StringUtil;
import com.grosup.ttzy.util.TtzyUtil;

@Controller
@RequestMapping("/wx/login")
public class WxLoginController {

    @Autowired
    private SessionService sessionService;

    private Logger logger = Logger.getLogger(WxLoginController.class);

    /**
     * 小程序授权登录
     * 
     * @param request
     * @param code
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "wxLogin")
    @ResponseBody
    public Map<String, Object> wxLogin(HttpServletRequest request,
            @RequestParam String code) {
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断code是否合法
        if (StringUtil.isNullOrEmpty(code)) {
            map.put("status", 0);
            map.put("msg", "code 不合法");
            return map;
        }

        // 微信小程序id
        String wxspAppid = "wxf1bac238f0e6a7f0";
        // 微信 app secret (小程序秘钥)
        String wxspSecret = "9bec3c2bcb203fcae846d6431401a004";
        // 用户授权
        String grant_type = "authorization_code";

        // 请求微信接口 用code 获取 session_key 和 openid
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret
                + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet(
                "https://api.weixin.qq.com/sns/jscode2session", params);
        // 转换为json
        JSONObject json = JSONObject.fromObject(sr);
        logger.info("请求微信接口返回的信息为" + json);
        // 会话密钥
        String session_key = json.get("session_key").toString();
        // 用户唯一标识
        String openid = (String) json.get("openid");
        // 微信用户在开放平台的唯一标识符
        // String unionid = (String) json.get("unionId");

        try {
            String third_session = session.getId();
            map.put("third_session", third_session);
            SessionBean sessionBean = new SessionBean(third_session,
                    session_key, openid);
            // 将session保存数据库
            sessionService.insertSessionValue(sessionBean);
            // ***根据unionId去数据库查询用户是否注册，如果未注册，session返回用户标识未注册
            // 并返回用户注册状态----未注册/注册还未通过审核，如果已经注册并通过审核，返回用户信息并写入session
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("openid", openid);
            UserBean userBean = TtzyUtil.getUserInfo(queryParams);
            if (userBean == null) {
                // 用户未注册
                map.put("userStatus", "unRegister");
            } else if ("1".equals(userBean.getStatus())) {
                // 用户已经注册还未通过审核
                map.put("userStatus", "unChecked");
            } else {
                map.put("userStatus", "checked");
                session.setAttribute("userId", userBean.getUid());
            }
            map.put("status", "success");
            map.put("msg", "校验登录成功");
        } catch (Exception e) {
            logger.error("校验登录失败");
        }
        return map;
    }
}
