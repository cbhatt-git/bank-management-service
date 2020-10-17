/**
 * @Author Vinu Sagar Maintained by Nooble ®
 * Licensed to Notyfyd
 */

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
public class GatewaySecurityConfiguration extends ResourceServerConfigurerAdapter {
	
	
	@Value( "${app.security.checkTokenUrl}" )
	private String CHECK_TOKEN_URL;
	
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth-server/**", "/actuator/**", "/user-service/users/signup")
                .permitAll()
                .antMatchers("/loan-service/**", "/actuator/**", "/user-service/users/signup")
                .permitAll()
        		.antMatchers("/**/v2/api-docs",
        				"/**/configuration/ui",
        				"/**/swagger-resources/**",
        				"/**/configuration/security",
        				"/**/swagger-ui.html",
        				"/**/webjars/**",
        				"/**/v3/api-docs/**",
        				"/**/swagger-ui/**")
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
