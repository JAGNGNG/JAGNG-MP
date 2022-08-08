package com.jagng.mp.dto;

import lombok.Data;

import java.util.Map;

/**
 * 发送模板消息
 */
@Data
public class SendDTO {

    /**
     * 接收人appid
     */
    private String touser;

    /**
     * 模板id
     */
    private String template_id;

    /**
     * 模板参数
     */
    private Map<String,TemplateParam> data;
}
