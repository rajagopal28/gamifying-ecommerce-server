package com.dxtrs.hack.gamify;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GamifiedStoreApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(GamifiedStoreApplication.class, args);
    }
}
