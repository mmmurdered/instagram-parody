package com.example.instagramparody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class InstagramParodyApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramParodyApplication.class, args);
    }

}
