package com.sidtech.loans;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.loans.controller") })
@EnableJpaRepositories("com.eazybytes.loans.repository")
@EntityScan("com.eazybytes.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Loan microservice REST API Documentation",
				description = "EazyBank Loan microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Salman Siddiqu",
						email = "salmansid8090@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://salmansiddiqui75.github.io/Portfolio/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "EazyBank Accounts microservice REST API Documentation",
				url = "https://salmansiddiqui75.github.io/Portfolio/"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}
}
