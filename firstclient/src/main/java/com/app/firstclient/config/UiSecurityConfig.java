package com.app.firstclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class UiSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${auth-server}/exit")
    private String logoutUrl;
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.antMatcher("/**")
			.authorizeRequests()
			.antMatchers("/", "/login**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.logout()
			.logoutSuccessUrl(logoutUrl)
			.and().authorizeRequests().anyRequest().authenticated();
	}

}
