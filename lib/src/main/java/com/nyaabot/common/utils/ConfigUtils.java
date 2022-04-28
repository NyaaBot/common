package com.nyaabot.common.utils;

import com.rabbitmq.client.Address;

/**
 * 配置读取工具类
 */
public class ConfigUtils {
    ConfigUtils() {
    }

    private static Address[] amqpAddresses;

    /**
     * 获取AMQP地址列表(以,分割)
     * 
     * @return AMQP地址列表
     */
    public static Address[] getAmqpAddresses() {
        if (amqpAddresses == null) {
            amqpAddresses = Address.parseAddresses(System.getenv("AMQP_ADDRESSES"));
        }
        return amqpAddresses;
    }

    private static String amqpUsername;

    /**
     * 获取AMQP用户名
     * 
     * @return AMQP用户名
     */
    public static String getAmqpUsername() {
        if (amqpUsername == null) {
            amqpUsername = System.getenv("AMQP_USERNAME");
        }
        return amqpUsername;
    }

    private static String amqpPassword;

    /**
     * 获取AMQP密码
     * 
     * @return AMQP密码
     */
    public static String getAmqpPassword() {
        if (amqpPassword == null) {
            amqpPassword = System.getenv("AMQP_PASSWORD");
        }
        return amqpPassword;
    }

    private static String amqpVirtualHost;

    /**
     * 获取AMQP虚拟主机
     * 
     * @return AMQP虚拟主机
     */
    public static String getAmqpVirtualHost() {
        if (amqpVirtualHost == null) {
            amqpVirtualHost = System.getenv("AMQP_VIRTUAL_HOST");
        }
        return amqpVirtualHost;
    }
}
