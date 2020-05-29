package me.brkn.tallyapi.core.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
    return registry -> registry.config()
        .commonTags("instance", "tomcat").commonTags("application", "tally-api")
        .commonTags("hikaricp", "tally-api");
  }

}
