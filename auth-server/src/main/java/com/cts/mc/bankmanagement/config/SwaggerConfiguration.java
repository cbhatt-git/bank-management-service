package com.cts.mc.bankmanagement.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() { 
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                       
				.apis(RequestHandlerSelectors.any())  
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "AUTHENTICATION SERVICE APIs", 
	      "APIs realted to OAuth2", 
	      "", 
	      "Terms of service", 
	      new Contact("Chiranjit Bhattacharya", "www.cognizant.com", "chranjit.bhattacharya.cognizant.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}

}
