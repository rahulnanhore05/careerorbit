package io.rahulnanhore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CareerorbitJobServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerorbitJobServiceApplication.class, args);
    }

}
