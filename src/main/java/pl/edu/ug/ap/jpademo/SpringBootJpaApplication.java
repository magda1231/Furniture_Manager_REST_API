package pl.edu.ug.ap.jpademo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.ug.ap.jpademo.services.FurnitureService;


@SpringBootApplication
public class SpringBootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(FurnitureService furnitureService) {
		return (args) -> {
			furnitureService.init();
		};
	}


}
