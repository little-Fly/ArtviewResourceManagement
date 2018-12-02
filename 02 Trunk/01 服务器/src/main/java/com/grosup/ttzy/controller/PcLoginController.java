package com.grosup.ttzy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grosup.ttzy.beans.UserBean;
import com.grosup.ttzy.service.UserService;
import com.grosup.ttzy.util.CodeUtil;
import com.grosup.ttzy.util.Constant;
import com.grosup.ttzy.util.CookieUtil;
import com.grosup.ttzy.util.GrosupException;
import com.grosup.ttzy.util.HttpRequest;
import com.grosup.ttzy.util.ObjectUtil;
import com.grosup.ttzy.util.StringUtil;

@Controller
@RequestMapping("/login")
public class PcLoginController {

    private static Logger logger = Logger.getLogger(PcLoginController.class);

    @Autowired
    private UserService userService;

    /**
     * PC授权登录
     * 
     * @param request
     * @param code
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "pcLogin")
    @ResponseBody
    public Map pcLogin(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String code,
            @RequestParam String noCheck) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断code是否合法
        if (StringUtil.isNullOrEmpty(code)) {
            map.put("code", CodeUtil.ERROR);
            map.put("msg", "code 不合法");
            return map;
        }

        // appid
        String appid = "wxb41c2d9e3682c984";
        // 微信 app secret (秘钥)
        String appSecret = "89742aa2e8e08bfa83fa6f8789621c02";
        // 用户授权
        String grant_type = "authorization_code";

        String params = "appid=" + appid + "&secret=" + appSecret + "&code="
                + code + "&grant_type=" + grant_type;
        String access_token_return_str = HttpRequest.sendGet(Constant.OUTH2_URL,
                params);

        // 转换为json
        JSONObject access_token_return_json = JSONObject
                .fromObject(access_token_return_str);
        logger.info("access_token_return_json is:" + access_token_return_json);
        // 用户唯一标识
        String openid = (String) access_token_return_json.get("openid");
        String access_token = (String) access_token_return_json
                .get("access_token");
        openid = (String) access_token_return_json.get("openid");
        String refreshToken = (String) access_token_return_json
                .get("refresh_token");
        try {
            // 通过access_token和openid获取微信用户信息
            String userInfo_url = "https://api.weixin.qq.com/sns/userinfo";
            String weixin_userInfo_Str = HttpRequest.sendGet(userInfo_url,
                    "access_token=" + access_token + "&openid=" + openid);
            JSONObject weixin_userInfo_json = JSONObject
                    .fromObject(weixin_userInfo_Str);
            String unionid = (String) weixin_userInfo_json.get("unionid");
            String nickname = (String) weixin_userInfo_json.get("nickname");
            // String headimgurl = (String) weixin_userInfo_json
            // .getString("headimgurl");

            // 通过微信unionid查询用户信息
            // UserBean user = userWS.getUserbeanbyWeixinUnionid(unionid);
            Map<String, Object> queryParam = new HashMap<String, Object>();
            // 为了测试
            if ("1".equals(noCheck)) {
                openid = "1";
            }
            if (null == openid) {
                map.put("code", -1);
                map.put("msg", "code无效");
                return map;
            }
            queryParam.put("openId", openid);
            UserBean user = userService.getUserInfo(queryParam);
            Map<String, Object> userInfo = new HashMap<String, Object>();
            if (ObjectUtil.isNull(user)) {
                // 用户未注册
                userInfo.put("userStatus", "unRegister");
            } else if ("1".equals(user.getStatus())) {
                // 用户已经注册还未通过审核
                userInfo.put("userStatus", "unChecked");
            } else {
                userInfo.put("userStatus", "checked");
            }
            map.put("userInfo", userInfo);
            map.put("code", 1);
            map.put("msg", "success");
            // new UserBean();
            // user.setUid(1L);
            // user.setOpenId("1111");
            // user.setNickName("薛利飞");
            // user.setCreateTime(new Date());
            // user.setLastTime(new Date());
            // 查询用户信息
            if (ObjectUtil.isNotNull(user)) {
                user.setLastValidTime(System.currentTimeMillis());
                CookieUtil.setCookies(response, user);
            }
        } catch (GrosupException e) {
            logger.error("登录认证失败", e);
        }
        return map;
    }
}
