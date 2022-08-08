package com.jagng.mp.job;

import com.jagng.mp.biz.SendBiz;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MorningJob {

    @Resource
    private SendBiz sendBiz;

    @Scheduled(cron = "0 0 8 * * ?")
    public void goodMorning(){
        sendBiz.send();
    }
}
