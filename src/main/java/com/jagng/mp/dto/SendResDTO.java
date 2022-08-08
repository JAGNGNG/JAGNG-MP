package com.jagng.mp.dto;

import lombok.Data;

/**
 * 发送模板消息返回
 */
@Data
public class SendResDTO {

    /**
     * 错误码
     */
    private Integer errorcode;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 消息id
     */
    private String msgid;
}
