package com.jagng.mp.controller;

import com.jagng.mp.biz.AuthBiz;
import com.jagng.mp.biz.SendBiz;
import com.jagng.mp.common.response.R;
import com.jagng.mp.dto.SendResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @description: 测试
 * @author: JAGNG
 * @create: 2022-08-07 13:26
 **/
@Controller
@RequestMapping("/test/")
@Slf4j
public class TestController {

    @Resource
    AuthBiz authBiz;

    @Resource
    SendBiz sendBiz;

    @GetMapping("testToken")
    @ResponseBody
    public R<String> testAccessToken(){
        return R.ok(authBiz.gennerateToken(),"测试完成");
    }

    @GetMapping("testSend")
    @ResponseBody
    public R<SendResDTO> testSend(){
        sendBiz.send();
        return R.ok("测试成功");
    }
}
