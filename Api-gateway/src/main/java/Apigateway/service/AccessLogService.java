package Apigateway.service;

import Apigateway.entity.AccessLog;
import Apigateway.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AccessLogService {
    @Autowired
    public AccessLogRepository accessLogRepository;
    public void saveAccessLog(String endpoint, String username) {
        AccessLog accessLog = new AccessLog();
        accessLog.setEndpoint(endpoint);
        accessLog.setUsername(username);
        accessLogRepository.save(accessLog);
    }
}
