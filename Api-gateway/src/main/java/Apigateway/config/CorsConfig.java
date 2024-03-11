package Apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig extends org.springframework.web.cors.CorsConfiguration{
        @Bean
        public CorsWebFilter corsFilter()
        {
            org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedOrigin("http://localhost:4200");
            corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
            corsConfiguration.addAllowedHeader("origin");
            corsConfiguration.addAllowedHeader("content-type");
            corsConfiguration.addAllowedHeader("accept");
            corsConfiguration.addAllowedHeader("authorization");
            corsConfiguration.addAllowedHeader("cookie");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return new CorsWebFilter(source);

        }
}
