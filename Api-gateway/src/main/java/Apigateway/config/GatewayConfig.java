package Apigateway.config;

import Apigateway.service.AccessLogService;
import Apigateway.service.implService.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public LoggingFilter loggingFilter(AccessLogService accessLogService) {
        return new LoggingFilter(accessLogService);
    }
}
