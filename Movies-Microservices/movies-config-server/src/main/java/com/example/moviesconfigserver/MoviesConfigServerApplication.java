package com.example.moviesconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MoviesConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesConfigServerApplication.class, args);
	}

}
