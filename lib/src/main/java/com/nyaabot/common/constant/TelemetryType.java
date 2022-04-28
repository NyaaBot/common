package com.nyaabot.common.constant;

/**
 * 遥测SDK对端类型
 */
public enum TelemetryType {
    /**
     * Jaeger
     */
    JAEGER("jaeger"),
    /**
     * Zipkin
     */
    ZIPKIN("zipkin"),
    /**
     * OpenTelemetry Protocol
     */
    OTLP("otlp"),
    /**
     * Logging exporter
     */
    LOGGING("logging"),
    /**
     * Prometheus
     */
    PROMETHEUS("prometheus");

    private String value;

    TelemetryType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Get TelemetryType from String
     * 
     * @param value TelemetryType String
     * @return TelemetryType
     */
    public static TelemetryType fromString(String value) {
        for (TelemetryType type : TelemetryType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
