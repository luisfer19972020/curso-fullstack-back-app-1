package com.curso.fullstack.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class })
public class ClientesAppBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesAppBackApplication.class, args);
	}

}
