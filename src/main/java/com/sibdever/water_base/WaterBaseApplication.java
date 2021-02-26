package com.sibdever.water_base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.sibdever.water_base.jwt")
public class WaterBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterBaseApplication.class, args);
    }

}
