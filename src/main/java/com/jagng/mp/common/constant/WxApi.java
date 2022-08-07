package com.jagng.mp.common.constant;

/**
 * @description: 微信接口集合
 * @author: JAGNG
 * @create: 2022-08-07 13:00
 **/
public interface WxApi {


    /**
     * 获取token
     */
    String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb33c067907e031f7&secret=a6c417db57219a562019ffb1b4a52149";


    /**
     * 创建草稿
     */
    String CREATE_ARTICLES = "https://api.weixin.qq.com/cgi-bin/draft/add?access_token=%s";

    String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=%s";


    /**
     * 发布
     */
    String PUBLISH_ARTICLES = "https://api.weixin.qq.com/cgi-bin/freepublish/submit?access_token=%s";

    /**
     * 天气
     */
    String TIAN_QI = "https://www.yiketianqi.com/free/day?appid=15987195&appsecret=7NeJJsOD&unescape=1&cityid=101270104";


    /**
     * 每日一句
     */
    String DAILY_NOTE = "http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&title=%s";


    /**
     * 群发
     */
    String SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";
}
