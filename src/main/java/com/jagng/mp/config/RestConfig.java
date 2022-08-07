package com.jagng.mp.config;

import com.jagng.mp.utils.JacksonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: rest配置
 * @author: JAGNG
 * @create: 2022-08-07 13:09
 **/
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(JacksonUtil.getObjectMapper());
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);
        mediaTypes.add(MediaType.APPLICATION_JSON);
        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        converters.add(0,jackson2HttpMessageConverter);
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}
