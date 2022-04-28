package com.nyaabot.common.model;

import java.util.Map;

import com.nyaabot.common.constant.RoutingKey;

import lombok.Data;

/**
 * 消息实体类
 */
@Data
public class Message {
    /**
     * 时间戳(ms) 默认情况下请使用<code>System.currentTimeMillis()</code>取得
     */
    private Long timestamp;

    /**
     * 路由标志
     */
    private RoutingKey routingKey;

    /**
     * 消息元数据
     */
    private Map<String, String> metadata;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 附加内容
     */
    private Object extra;

}
