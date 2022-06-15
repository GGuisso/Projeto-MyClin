package com.myclin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
			.antMatchers(
					"/api/clinica/**",
					"/api/user/**").permitAll()
			.antMatchers(
					"/api/agenda/**",
					"/api/consulta/**",
					"/api/funcao/**",
					"/api/funcionarios/**",
					"/api/pacientes/**").authenticated()
			.anyRequest().denyAll();
	}

}
