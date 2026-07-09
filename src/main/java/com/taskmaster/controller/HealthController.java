package com.taskmaster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
@RestController
public class HealthController {

    private static final Logger logger =
            LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/health")
    public Map<String, Object> health() {

        logger.info("Health endpoint invoked");

        return Map.of(
                "status", "UP",
                "application", "TaskMaster",
                "timestamp", LocalDateTime.now()
        );
    }
}
