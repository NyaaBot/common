package com.nyaabot.common;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;

import com.nyaabot.common.model.Message;
import com.nyaabot.common.utils.ConfigUtils;
import com.nyaabot.common.utils.OpenTelemetryUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

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

    /**
     * 声明队列
     * 
     * @param queueName 队列名称
     * @throws IOException
     * @throws TimeoutException
     */
    public void declareQueue(String queueName) throws IOException, TimeoutException {
        Span span = tracer.spanBuilder("Start to declare queue " + queueName).startSpan();
        try (Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, true, false, false, null);
        } catch (IOException | TimeoutException e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }

    /**
     * 声明交换机
     * 
     * @param exchangeName 交换机名称
     * @throws IOException
     * @throws TimeoutException
     */
    public void declareExchange(String exchangeName) throws IOException, TimeoutException {
        Span span = tracer.spanBuilder("Start to declare exchange " + exchangeName).startSpan();
        try (Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(exchangeName, "topic", true);
        } catch (IOException | TimeoutException e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }

    /**
     * 绑定队列到交换机
     * 
     * @param queueName    队列名称
     * @param exchangeName 交换机名称
     * @param routingKey   路由键
     * @throws IOException
     * @throws TimeoutException
     */
    public void bindQueue(String queueName, String exchangeName, String routingKey)
            throws IOException, TimeoutException {
        Span span = tracer.spanBuilder("Start to bind queue " + queueName + " to exchange " + exchangeName).startSpan();
        try (Channel channel = connection.createChannel()) {
            channel.queueBind(queueName, exchangeName, routingKey);
        } catch (IOException | TimeoutException e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }

    /**
     * 发送消息
     * 
     * @param exchangeName 交换机名称
     * @param message      消息
     */
    public abstract void sendMessage(String exchangeName, Message message);

    /**
     * 绑定消息接收处理器
     * 
     * @param queueName 队列名称
     * @param callback  消息接收回调函数
     */
    public abstract void bindCallback(String queueName, DeliverCallback callback);

}
