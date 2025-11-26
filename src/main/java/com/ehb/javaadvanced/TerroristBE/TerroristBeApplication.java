package com.ehb.javaadvanced.TerroristBE;

import com.ehb.javaadvanced.TerroristBE.ingestion.ImportCoordinator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TerroristBeApplication {

	public static void main(String[] args) {
        SpringApplication.run(TerroristBeApplication.class, args);
	}

    /* @TODO
          Run the import at start but schedule it afterwards
          Enrich the GraphQL queries to make the search with other and various criterias
            also  foresee queries like '%xxx%' alike ...

          If time left => foresee to split cell multiple line in to an extra table linked by Id
                so that the query like can include the related ...
    *
    *
    * */

    @Bean
    public CommandLineRunner runner(ImportCoordinator coordinator) {
        return args -> {
                    coordinator.runImport();
            };
    }

}
