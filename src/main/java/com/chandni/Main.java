package com.chandni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.out.println("Sample REST api");
        SpringApplication.run(Main.class, args);
    }
}