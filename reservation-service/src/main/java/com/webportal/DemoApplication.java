package com.webportal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableBinding(Sink.class)
@EnableDiscoveryClient
@SpringBootApplication // @IWantToGoHomeEarly
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    HealthIndicator ccc() {
        return () -> Health.status("I <3 Chicago!").build();
    }

    @Bean
    CommandLineRunner runner(ReservationRepository rr) {
        return args -> {

            Arrays.asList("Amarendra,Prakriti,Sonia,Nadeem,Sanal,Ashwini".split(","))
                    .forEach(n -> rr.save(new Reservation(n)));

            rr.findAll().forEach(System.out::println);

        };
    }
}


