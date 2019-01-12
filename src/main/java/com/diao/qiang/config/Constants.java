package com.diao.qiang.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.diao.qiang.constants")
public class Constants {

    public static final String URL = "https://mma.qq.com/cgi-bin/im/online";

//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
