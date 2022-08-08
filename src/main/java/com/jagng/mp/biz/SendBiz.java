package com.jagng.mp.biz;

import cn.hutool.core.date.ChineseDate;
import com.jagng.mp.common.constant.CommonConstants;
import com.jagng.mp.common.constant.WxApi;
import com.jagng.mp.dto.*;
import com.jagng.mp.utils.JacksonUtil;
import com.jagng.mp.utils.WxTemplateUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Component
public class SendBiz {


    @Resource
    RestTemplate restTemplate;

    @Resource
    AuthBiz authBiz;

    @SneakyThrows
    public void send() {
        //获取天气
        TianQiDTO tianQi = restTemplate.getForObject(WxApi.TIAN_QI, TianQiDTO.class);
        //每日一句
        String dailyUrl = String.format(WxApi.DAILY_NOTE, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        DailyNoteDTO dailyNote = restTemplate.getForObject(dailyUrl, DailyNoteDTO.class);
        //组建模板参数
        Map<String, TemplateParam> paramMap = new TreeMap<>();
        WxTemplateUtil.put(paramMap, "first", "早安 o(*^＠^*)o敏酱", "");
        WxTemplateUtil.put(paramMap, "date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")), "#A9A9A9");
        WxTemplateUtil.put(paramMap, "week", tianQi.getWeek(), "#A9A9A9");
        WxTemplateUtil.put(paramMap, "meetCount", DAYS.between(LocalDate.of(2021, 4, 10), LocalDate.now()) + "", "#9370D8");
        WxTemplateUtil.put(paramMap, "loveCount", DAYS.between(LocalDate.of(2021, 7, 24), LocalDate.now()) + "", "#FF69B4");
        ChineseDate chineseDate = new ChineseDate(LocalDate.now().getYear(), 9, 15);
        WxTemplateUtil.put(paramMap, "birthdayCount", DAYS.between(LocalDate.now(), chineseDate.getGregorianDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) + "", "#FA8072");
        WxTemplateUtil.put(paramMap, "tem_day", tianQi.getTemDay(), "#DC143C");
        WxTemplateUtil.put(paramMap, "wea", tianQi.getWea(), "#FFA07A");
        WxTemplateUtil.put(paramMap, "tem_night", tianQi.getTemNight(), "#8FBC8F");
        WxTemplateUtil.put(paramMap, "remark", dailyNote.getNote(), "#FFB6C1");
        //获取微信accessToken
        String accessToken = authBiz.gennerateToken();
        log.info("accessToken:{}", accessToken);
        //发送模板消息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SendDTO sendDTO = new SendDTO();
        sendDTO.setData(paramMap);
        sendDTO.setTemplate_id("KnjH_blvsDMDGuRZjkPlATXQ_hfHWavV3IzLGK3iosc");
        List<String> openIds = Arrays.asList("oGpFj6GgEbFFfDpqKv0MWpASATdc","oGpFj6MCq1-MdVl3xMGVzj9Aq_Vo");
        for (String openId : openIds) {
            sendDTO.setTouser(openId);
            log.info("SendDTO:{}", JacksonUtil.getObjectMapper().writeValueAsString(sendDTO));
            HttpEntity<SendDTO> entity = new HttpEntity<>(sendDTO, headers);
            SendResDTO sendRes = restTemplate.postForObject(String.format(WxApi.SEND, accessToken), entity, SendResDTO.class);
            log.info("sendRes:{}", sendRes);
        }
    }


}
