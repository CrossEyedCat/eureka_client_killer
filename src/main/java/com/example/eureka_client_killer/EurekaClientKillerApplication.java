package com.example.eureka_client_killer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientKillerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientKillerApplication.class, args);
    }
    @PostMapping("/dragon/{dragonId}/kill")
    public String killDragon(@PathVariable("dragonId") int dragonId) {
        RestTemplate restTemplate = new RestTemplate();
        String dragonServiceUrl = "http://dragon-service/dragons/" + dragonId;
        try {
            restTemplate.delete(dragonServiceUrl);
            return "<message>Dragon killed successfully</message>";
        } catch (Exception e) {
            return "<error>Failed to communicate with dragon service</error>";
        }
    }
}
