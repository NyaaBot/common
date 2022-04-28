package com.nyaabot.common.model;

import com.nyaabot.common.constant.RoutingKey;

import lombok.Data;

/**
 * 频道实体类
 */
@Data
public class Channel {
    /**
     * 频道名称
     */
    private String name;

    /**
     * 频道的交换机名(用于AMQP队列绑定)
     */
    private String exchangeName;

    /**
     * 频道的队列名(用于AMQP队列绑定)
     */
    private String queueName;

    /**
     * 路由标志(用于AMQP队列绑定)
     */
    private RoutingKey routingKey;
}
