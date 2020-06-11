package com.guli.guli_ucenter.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("/api/ucenter/wx")
@CrossOrigin
@Controller
@Slf4j
public class WXApiController {
    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    @Value("${wx.open.state}")
    private String state;
    @GetMapping("login")
    @ApiOperation("生成二维码")
    public String genQrConnect(){
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        try {
            redirectUrl=URLEncoder.encode(redirectUrl,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String qrcodeUrl = String.format(baseUrl,appId,redirectUrl,state);
        log.debug(qrcodeUrl);
        return "redirect:"+qrcodeUrl;
    }

    @GetMapping("callback")
    public void callback(String code,String state){
        log.debug("code:{},state:{}",code,state);
    }
}
