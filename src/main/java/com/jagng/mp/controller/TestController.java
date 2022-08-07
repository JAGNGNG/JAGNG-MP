package com.jagng.mp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jagng.mp.biz.AuthBiz;
import com.jagng.mp.biz.PublishBiz;
import com.jagng.mp.common.response.R;
import com.jagng.mp.dto.SendAllResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Objects;

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
    PublishBiz publishBiz;

    @GetMapping("testToken")
    @ResponseBody
    public R<String> testAccessToken(){
        return R.ok(authBiz.gennerateToken(),"测试完成");
    }

    @GetMapping("testPublish")
    @ResponseBody
    public R<SendAllResDTO> testPublish(){
        SendAllResDTO result = new SendAllResDTO();
        try {
             result = publishBiz.publish();
        } catch (JsonProcessingException e) {
            log.info("接口发生异常:",e);
        }
        return Objects.nonNull(result)?R.ok(result,"测试完成"):R.failed("测试失败");
    }
}
