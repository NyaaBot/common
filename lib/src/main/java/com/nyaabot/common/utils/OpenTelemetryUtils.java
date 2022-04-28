package com.nyaabot.common.utils;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;

/**
 * 自动创建遥测对象
 */
public class OpenTelemetryUtils {
    OpenTelemetryUtils() {
    }

    private static OpenTelemetry openTelemetry;

    /**
     * 获取OpenTelemetry对象
     * 
     * @return OpenTelemetry对象
     */
    public static OpenTelemetry getOpenTelemetry() {
        if (openTelemetry == null) {
            openTelemetry = AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
        }
        return openTelemetry;
    }
}
