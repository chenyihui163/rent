package com.rent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Global {
    @Value("${spring.application.name}")
    private String name;
    @Value("${rent-oath.token.key}")
    private String key;
    public static final String UAS_URL="http://127.0.0.1:8080";
    public static final String APP_ID="a66abb5684c45962d887564f08346e8d";
    public String getKey() {
        return key;
    }


    public String getName() {
        return name;
    }

}
