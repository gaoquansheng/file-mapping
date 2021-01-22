package com.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "bear")
public class BearConfig {
    private static String path;

    public static String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
