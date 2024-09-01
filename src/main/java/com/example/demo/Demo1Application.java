package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@SpringBootApplication
@RestController
@RequestMapping
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Autowired
    private WebClient callBackWebClient12;
    @Autowired
    private WebClient callBackWebClient13;

    @RequestMapping("/2")
    public String get1_2() {
        System.out.println("TLS 1_2");
        return callBackWebClient12.post()
                .uri("https://crowdfund-api-staging-v2.leannode.com/user/elm/nafath/callback")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //return "";
    }

    @RequestMapping("/3")
    public String get1_3() {
        System.out.println("TLS 1_3");
        return callBackWebClient13.post()
                .uri("https://crowdfund-api-staging-v2.leannode.com/user/elm/nafath/callback")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //return "";
    }

}
