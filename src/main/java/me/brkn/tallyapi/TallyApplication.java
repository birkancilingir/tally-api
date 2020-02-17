package me.brkn.tallyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.metrics.export.prometheus.EnablePrometheusMetrics;

@SpringBootApplication
public class TallyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TallyApplication.class, args);
    }

}
