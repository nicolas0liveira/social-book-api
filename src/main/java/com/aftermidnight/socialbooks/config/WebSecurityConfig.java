package com.aftermidnight.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * lembrete para autenticação! cabeçalho HTTP: Authorization 'Basic B64(usuario:senha)'
		 */
		auth.inMemoryAuthentication().withUser("user").password("password").roles("AUTHENTICATED");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll() //a uri /h2-console vai bypassar a autenticação
		.anyRequest().authenticated() //todas as requisições devem ser autenticadas
			.and()
				.httpBasic() //metodo de autenticação (Podem ser outros)
			.and()
				.csrf().disable(); //proteção para evitar ataques csrf
	}
}
