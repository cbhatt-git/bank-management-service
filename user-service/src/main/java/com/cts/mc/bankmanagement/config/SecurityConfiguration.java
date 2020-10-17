package com.cts.mc.bankmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {

	@Value( "${app.security.checkTokenUrl}" )
	private String CHECK_TOKEN_URL;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
		.headers()
		.frameOptions()
		.disable();
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/actuator/**", "/users/signup", "/h2/**")
		.permitAll()
		.antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**",
				"/v3/api-docs/**",
				"/swagger-ui/**")
		.permitAll()
		.antMatchers("/**")
		.authenticated();
	}


	@Bean
	public RemoteTokenServices remoteTokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices();
		tokenService.setCheckTokenEndpointUrl(CHECK_TOKEN_URL);
		tokenService.setClientId("client");
		tokenService.setClientSecret("secret");
		return tokenService;
	}
}
