package com.nyaabot.common.utils;

import com.nyaabot.common.constant.TelemetryType;

/**
 * 配置读取工具类
 */
public class ConfigUtils {
    private ConfigUtils() {
    }

    private static String openTelemetryServiceName;

    /**
     * Get the OpenTelemetry service name.
     * 
     * @return The OpenTelemetry service name.
     */
    public static String getOpenTelemetryServiceName() {
        if (openTelemetryServiceName == null) {
            openTelemetryServiceName = System.getenv("OPEN_TELEMETRY_SERVICE_NAME");
        }
        return openTelemetryServiceName;
    }

    private static String openTelemetryEndpoint;

    /**
     * Get the OpenTelemetry endpoint.
     * 
     * @return The OpenTelemetry endpoint.
     */
    public static String getOpenTelemetryEndpoint() {
        if (openTelemetryEndpoint == null) {
            openTelemetryEndpoint = System.getenv("OPEN_TELEMETRY_ENDPOINT");
        }
        return openTelemetryEndpoint;
    }

    private static TelemetryType openTelemetryType;

    /**
     * Get the OpenTelemetry type.
     * 
     * @return The OpenTelemetry type.
     */
    public static TelemetryType getOpenTelemetryType() {
        if (openTelemetryType == null) {
            openTelemetryType = TelemetryType.fromString(System.getenv("OPEN_TELEMETRY_TYPE"));
        }
        return openTelemetryType;
    }

}
