package com.nanjolono.payment.config;


import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthConfig implements HealthIndicator {


    @Override
    public Health health() {
        return new Health.Builder().up().build();
    }
}
