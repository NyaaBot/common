package com.nyaabot.common.constant;

/**
 * 路由常量
 */
public enum Routing {
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

    Routing(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
