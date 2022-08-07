package com.jagng.mp.biz;

import com.jagng.mp.common.constant.WxApi;
import com.jagng.mp.dto.AccessTokenDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;

/**
 * @description: 微信鉴权
 * @author: JAGNG
 * @create: 2022-08-07 13:04
 **/
@Component
public class AuthBiz {

    @Resource
    RestTemplate restTemplate;

    /**
    * 获取token
    * @date 2022/8/7 13:18
    * @return String
    **/
    public String gennerateToken(){
        AccessTokenDTO accessTokenDTO =  restTemplate.getForObject(WxApi.ACCESS_TOKEN, AccessTokenDTO.class);
        Assert.notNull(accessTokenDTO,"微信返回token信息异常");
        return accessTokenDTO.getAccessToken();
    }

}
