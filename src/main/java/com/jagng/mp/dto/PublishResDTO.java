package com.jagng.mp.dto;

import lombok.Data;

/**
 * @description: 素材返回
 * @author: JAGNG
 * @create: 2022-08-07 14:54
 **/
@Data
public class PublishResDTO {

    /**
     * 错误码
     */
    private Integer errcode;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 发布任务的id
     */
    private String publish_id;
}
