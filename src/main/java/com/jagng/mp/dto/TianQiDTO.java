package com.jagng.mp.dto;

import lombok.Data;

/**
 * @description: 天气
 * @author: JAGNG
 * @create: 2022-08-07 15:19
 **/
@Data
public class TianQiDTO {


    /**
     * 城市
     */
    private String city;

    /**
     * 日期
     */
    private String date;

    /**
     * 星期
     */
    private String week;

    /**
     * 天气
     */
    private String wea;

    /**
     * 白天温度(高温)
     */
    private String temDay;

    /**
     * 白天温度(低温温)
     */
    private String temNight;


}
