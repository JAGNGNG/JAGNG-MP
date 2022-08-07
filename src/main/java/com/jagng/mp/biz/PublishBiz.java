package com.jagng.mp.biz;

import cn.hutool.core.date.ChineseDate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jagng.mp.common.constant.CommonConstants;
import com.jagng.mp.common.constant.WxApi;
import com.jagng.mp.dto.*;
import com.jagng.mp.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @description: 发布
 * @author: JAGNG
 * @create: 2022-08-07 15:57
 **/
@Slf4j
@Component
public class PublishBiz {

    @Resource
    RestTemplate restTemplate;

    @Resource
    AuthBiz authBiz;

    public SendAllResDTO publish() throws JsonProcessingException {
        //获取天气
        TianQiDTO tianQi =restTemplate.getForObject(WxApi.TIAN_QI,TianQiDTO.class);
        //每日一句
        String dailyUrl = String.format(WxApi.DAILY_NOTE, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        DailyNoteDTO dailyNote = restTemplate.getForObject(dailyUrl,DailyNoteDTO.class);
        //组建消息content
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("date",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        paramMap.put("week",tianQi.getWeek());
        paramMap.put("meetCount", DAYS.between(LocalDate.of(2021,4,10),LocalDate.now())+"");
        paramMap.put("loveCount", DAYS.between(LocalDate.of(2021,7,24),LocalDate.now())+"");
        ChineseDate chineseDate = new ChineseDate(LocalDate.now().getYear(),9,15);
        paramMap.put("birthdayCount", DAYS.between(LocalDate.now(),chineseDate.getGregorianDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())+"");
        paramMap.put("wea",tianQi.getWea());
        paramMap.put("tem_day",tianQi.getTemDay());
        paramMap.put("tem_night",tianQi.getTemNight());
        paramMap.put("note",dailyNote.getNote());
        StringSubstitutor stringSubstitutor = new StringSubstitutor(paramMap);
        String content = stringSubstitutor.replace(CommonConstants.TEXT_TEMPLATE);
        log.info("content:{}",content);
        log.info("contentSize:{}",getByteSize(content));
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle("来自的阿姜请安┗|｀O′|┛ 嗷~~");
        articleDTO.setContent(content);
        articleDTO.setThumbMediaId("iFgRaxnck_ZyjYlwgHfT9rPi8-Yqhy7GlEcJ4I9HcCu9awWo7SLARvHcTSz_wb8d");
        //创建header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //创建素材
        String accessToken = authBiz.gennerateToken();
        log.info("accessToken:{}",accessToken);
//        log.info("articleDTO：{}", JacksonUtil.getObjectMapper().writeValueAsString(articleDTO));
//        List<ArticleDTO> articleDTOS = new ArrayList<>();
//        articleDTOS.add(articleDTO);
//        ArticleListDTO articleListDTO = new ArticleListDTO();
//        articleListDTO.setArticles(articleDTOS);
//        HttpEntity<ArticleListDTO> articleDTOHttpEntity = new HttpEntity<>(articleListDTO,headers);
//        ArticleResDTO articleResDTO = restTemplate.postForObject(String.format(WxApi.UPLOAD_NEWS,accessToken),articleDTOHttpEntity,ArticleResDTO.class);
//        log.info("articleResDTO：{}", JacksonUtil.getObjectMapper().writeValueAsString(articleResDTO));
//        //发送推文
//        PublishDTO publishDTO = new PublishDTO();
//        publishDTO.setMediaId("iFgRaxnck_ZyjYlwgHfT9j77DA_pCeYR328-hOPbTWuaSflCa1FwRvIznbHKxiBx");
//        log.info("publishDTO：{}", JacksonUtil.getObjectMapper().writeValueAsString(publishDTO));
//        HttpEntity<PublishDTO> publishDTOHttpEntity = new HttpEntity<>(publishDTO,headers);
//        PublishResDTO publishResDTO = restTemplate.postForObject(String.format(WxApi.PUBLISH_ARTICLES,accessToken),publishDTOHttpEntity,PublishResDTO.class);
//        log.info("publishResDTO：{}", JacksonUtil.getObjectMapper().writeValueAsString(publishResDTO));
        SendAllDTO sendAllDTO = new SendAllDTO();
        SendAllDTO.Filter filter = new SendAllDTO.Filter();
        filter.setIs_to_all(true);
        sendAllDTO.setFilter(filter);
//        SendAllDTO.Mpnews mpnews = new SendAllDTO.Mpnews();
//        mpnews.setMediaId(articleResDTO.getMediaId());
//        sendAllDTO.setMpnews(mpnews);
        SendAllDTO.Text text = new SendAllDTO.Text();
        text.setContent(content);
        sendAllDTO.setText(text);

        sendAllDTO.setMsgtype("text");
        HttpEntity<SendAllDTO> sendParam = new HttpEntity<>(sendAllDTO,headers);
        SendAllResDTO sendAllResDTO = restTemplate.postForObject(String.format(WxApi.SEND_ALL,accessToken),sendParam,SendAllResDTO.class);
        return sendAllResDTO;
    }

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     *
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编码时占3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return size;
    }
}
