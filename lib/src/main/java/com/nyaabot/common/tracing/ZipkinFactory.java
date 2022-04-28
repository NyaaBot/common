package com.nyaabot.common.tracing;

import com.nyaabot.common.utils.ConfigUtils;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;

/**
 * OpenTelemetry Zipkin SDK
 */
public class ZipkinFactory {
    private ZipkinFactory() {
    }

    /**
     * Adds a SimpleSpanProcessor initialized with ZipkinSpanExporter to the
     * TracerSdkProvider
     */
    static OpenTelemetry initOpenTelemetry() {
        String endpoint = ConfigUtils.getOpenTelemetryEndpoint();
        String serviceName = ConfigUtils.getOpenTelemetryServiceName();
        ZipkinSpanExporter zipkinExporter = ZipkinSpanExporter.builder().setEndpoint(endpoint).build();

        Resource serviceNameResource = Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, serviceName));

        // Set to process the spans by the Zipkin Exporter
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(SimpleSpanProcessor.create(zipkinExporter))
                .setResource(Resource.getDefault().merge(serviceNameResource))
                .build();
        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder().setTracerProvider(tracerProvider)
                .buildAndRegisterGlobal();

        // add a shutdown hook to shut down the SDK
        Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::close));

        // return the configured instance so it can be used for instrumentation.
        return openTelemetry;
    }
}
