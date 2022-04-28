package com.nyaabot.common.tracing;

import com.nyaabot.common.utils.ConfigUtils;

import io.opentelemetry.api.OpenTelemetry;

/**
 * Generator OpenTelemetry Instance
 */
public class TelemetryFactory {
    private TelemetryFactory() {
    }

    /**
     * Get a OpenTelemetry instance.
     * 
     * @return OpenTelemetry instance
     */
    public static OpenTelemetry getTelemetryInstance() {
        OpenTelemetry telemetry;
        switch (ConfigUtils.getOpenTelemetryType()) {
            case JAEGER:
                telemetry = JaegerFactory.initOpenTelemetry();
                break;
            case ZIPKIN:
                telemetry = ZipkinFactory.initOpenTelemetry();
                break;
            case OTLP:
                throw new UnsupportedOperationException();
            case PROMETHEUS:
                throw new UnsupportedOperationException();
            case LOGGING:
            default:
                telemetry = LoggingFactory.initOpenTelemetry();
                break;
        }
        return telemetry;
    }
}
