package com.ehb.javaadvanced.TerroristBE;

import com.ehb.javaadvanced.TerroristBE.ingestion.ImportCoordinator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TerroristBeApplication {

	public static void main(String[] args) {
        SpringApplication.run(TerroristBeApplication.class, args);
	}

    @Bean
    public CommandLineRunner runner(ImportCoordinator coordinator) {
        return args -> {
                    coordinator.runImport();
            };
    }

}
