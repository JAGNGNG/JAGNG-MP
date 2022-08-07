package com.jagng.mp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 获取token
 * @author JAGNG
 * @date  2022-08-07 13:11
 **/
@Data
public class AccessTokenDTO {

    /**
     * token
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private Long expiresIn;
}
