package com.jagng.mp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @description: 群发
 * @author: JAGNG
 * @create: 2022-08-07 17:34
 **/
@Data
public class SendAllDTO {

    private Filter filter;

    private Mpnews mpnews;

    private String msgtype;

    private Text text;



    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Filter{
        private Boolean is_to_all;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Mpnews{
        private String mediaId;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Text{
        private String content;
    }
}
