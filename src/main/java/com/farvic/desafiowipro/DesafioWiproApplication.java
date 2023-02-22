package com.farvic.desafiowipro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class DesafioWiproApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioWiproApplication.class, args);
    }

}
