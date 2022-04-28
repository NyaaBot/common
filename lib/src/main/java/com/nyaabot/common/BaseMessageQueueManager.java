package com.nyaabot.common;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;

import com.nyaabot.common.utils.ConfigUtils;
import com.nyaabot.common.utils.OpenTelemetryUtils;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息队列管理器基础类，每个Adapter和Processor都需要继承该类
 */
public abstract class BaseMessageQueueManager {
    private Tracer tracer;
    private Connection connection;

    BaseMessageQueueManager() throws IOException, TimeoutException {
        tracer = OpenTelemetryUtils.getOpenTelemetry().getTracer(this.getClass().getCanonicalName());
        initConnection();
    }

    private void initConnection() throws IOException, TimeoutException {
        Span span = tracer.spanBuilder("Start to get connection to mq").startSpan();
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUsername(ConfigUtils.getAmqpUsername());
            factory.setPassword(ConfigUtils.getAmqpPassword());
            factory.setVirtualHost(ConfigUtils.getAmqpVirtualHost());
            connection = factory.newConnection(ConfigUtils.getAmqpAddresses());
        } catch (IOException | TimeoutException e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }

}
