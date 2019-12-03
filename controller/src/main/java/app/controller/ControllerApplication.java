package app.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;
@EnableAutoConfiguration()
@SpringBootApplication
@ComponentScan(basePackages="app")
@EnableJpaRepositories(basePackages="app.repository")
@PropertySource("application.properties")
public class ControllerApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(ControllerApplication.class, args);
	}

}
