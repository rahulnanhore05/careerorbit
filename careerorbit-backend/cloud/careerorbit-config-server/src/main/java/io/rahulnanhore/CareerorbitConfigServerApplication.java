package io.rahulnanhore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CareerorbitConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerorbitConfigServerApplication.class, args);
	}

}
