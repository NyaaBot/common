package com.nyaabot.common.constant;

/**
 * 路由常量
 */
public enum RoutingKey {
    /**
     * 路由前队列
     */
    PREROUTING("PREROUTING"),
    /**
     * 转发队列
     */
    FORWARD("FORWARD"),
    /**
     * 输出队列
     */
    OUTPUT("OUTPUT"),
    /**
     * 路由后队列
     */
    POSTROUTING("POSTROUTING");

    private String value;

    RoutingKey(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
