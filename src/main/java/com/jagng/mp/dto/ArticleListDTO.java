package com.jagng.mp.dto;

import lombok.Data;

import java.util.List;

/**
 * @description: 素材list
 * @author: JAGNG
 * @create: 2022-08-07 17:07
 **/
@Data
public class ArticleListDTO {

    private List<ArticleDTO> articles;
}
