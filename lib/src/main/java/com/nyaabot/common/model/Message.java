package com.nyaabot.common.model;

import lombok.Data;

@Data
public class Message {
    /**
     * 时间戳(ms) 默认情况下请使用<code>System.currentTimeMillis()</code>取得
     */
    private Long timestamp;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 附加内容
     */
    private Object extra;
}
