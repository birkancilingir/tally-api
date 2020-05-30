package me.brkn.tallyapi.core.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {

  @Bean
  MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() throws UnknownHostException {
    InetAddress IP = InetAddress.getLocalHost();

    return registry -> registry.config()
        .commonTags("instance", IP.getHostAddress())
        .commonTags("application", "tally-api")
        .commonTags("hikaricp", "tally-api");
  }

}
