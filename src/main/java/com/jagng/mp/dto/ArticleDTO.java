package com.jagng.mp.dto;

import lombok.Data;

/**
 * @description: 草稿
 * @author: JAGNG
 * @create: 2022-08-07 14:50
 **/
@Data
public class ArticleDTO {

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    private String content;

    /**
     * 缩略图
     */
    private String thumbMediaId;
}
