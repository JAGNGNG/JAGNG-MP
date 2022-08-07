package com.jagng.mp.dto;

import lombok.Data;

/**
 * @description: 群发
 * @author: JAGNG
 * @create: 2022-08-07 17:41
 **/
@Data
public class SendAllResDTO {

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    private String msgId;

    private String msgDataId;
}
