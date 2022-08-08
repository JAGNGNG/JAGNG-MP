package com.jagng.mp.utils;

import com.jagng.mp.dto.TemplateParam;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 微信模板工具类
 */
public class WxTemplateUtil {

    /**
     * wx模板参数put
     * @param data 模板参数
     * @param key 参数key
     * @param value 参数value
     * @param color 参数字体颜色
     */
    public static void put(Map<String, TemplateParam> data,String key,String value,String color){
        Assert.notNull(value,"模板参数不能为空!");
        TemplateParam param = new TemplateParam();
        param.setValue(value);
        param.setColor(color);
        data.put(key,param);
    }
}
