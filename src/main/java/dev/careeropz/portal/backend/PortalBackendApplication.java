package dev.careeropz.portal.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PortalBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(PortalBackendApplication.class, args);
	}

}
