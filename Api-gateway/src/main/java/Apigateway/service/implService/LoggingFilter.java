package Apigateway.service.implService;

import Apigateway.service.AccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {

    @Autowired
    public AccessLogService accessLogService;
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public LoggingFilter(AccessLogService accessLogService) {

    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpMethod method = request.getMethod();
        URI uri= request.getURI();
        HttpHeaders headers = request.getHeaders();
        String username= " ";

        logger.info("Request: {} {}", method,uri);
        logger.info("Headers: {}", headers);

        accessLogService.saveAccessLog(uri.getPath(), username);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpResponse response = exchange.getResponse();
            HttpStatusCode status = response.getStatusCode();
            HttpHeaders responseHeaders = response.getHeaders();

            // Log response details
            logger.info("Response Status: {}", status);
            logger.info("Response Headers: {}", responseHeaders);
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
