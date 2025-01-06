package com.app.BankingApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Banking Application Documentation",
				description = "Backend REST API for Banking Application",
				contact = @Contact(
						name = "Prem Lalwani",
						email = "premlalwani1209@gmail.com",
						url = "https://github.com/premlalwani09"
				),
				license = @License(
						name = "Prem Lalwani",
						url = "https://github.com/premlalwani09"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Banking Application Documentation",
				url = "https://github.com/premlalwani09"
		)
)
public class BankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
	}

}
