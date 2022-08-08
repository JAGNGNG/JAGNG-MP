package com.jagng.mp.common.constant;

/**
 * @description: 公用常量
 * @author: JAGNG
 * @create: 2022-08-07 13:29
 **/
public interface CommonConstants {
    /**
     * 成功标记
     */
    Integer SUCCESS = 0;

    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 消息模板
     */
    String MSG_TEMPLATE = "<h1>早安 o(*^＠^*)o敏酱</h1>\n" +
            "<h2>${date} ${week} </h2>\n" +
            "<p>今天是我们认识的第<span style=\"color:#FFB6C1\">${meetCount}</span>天,也是我们相恋的第<span style=\"color:#FF69B4\">${loveCount}</span>天</p>\n" +
            "<p>距离你的生日还有<span style=\"color:#FA8072\">${birthdayCount}</span>天</p>\n" +
            "<p>今日天气 <span style=\"color:#FFA07A\">${wea}</span></p>\n" +
            "<p>白天温度(高温) <span style=\"color:#DC143C\">${tem_day}</span>度</p>\n" +
            "<p>白天温度(低温) <span style=\"color:#8FBC8F\">${tem_night}</span>度</p>\n" +
            "<p>1出门记得注意增减衣物╰(*°▽°*)╯</p>\n" +
            "<p>${note}</p>\n" +
            "<p>今天也要元气满满哦（づ￣3￣）づ╭❤～</p>";

    /**
     * 消息模板
     */
    String TEXT_TEMPLATE =
            "${date} ${week} \n" +
            "第${meetCount}天,第${loveCount}天" +
            "距离还有${birthdayCount}天\n" +
            "今日天气 ${wea} \n" +
            "白天温度(高温) ${tem_day}度\n" +
            "白天温度(低温) ${tem_night}度\n" +
            "出门记得注意增减衣物╰(*°▽°*)╯\n" +
            "${note}\n" +
            "今天也要元气满满哦（づ￣3￣）づ╭❤～";
}
