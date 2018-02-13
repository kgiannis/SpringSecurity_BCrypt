package com.rt.jdbc_bcrypt_pass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/** Inject userDetailsService for matching hashed passwords */
	@Autowired
	UserDetailsService userDetailsService;
	
	
	/** Inject BCrypt for hashed passwords */
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/** Configure authentication manager */
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder( bCryptPasswordEncoder );
	}
	
	
	/**
	 * Authorize Requests.
	 * Paths: '/' available for all
	 * Paths: '/admin' and 'admin/*' only for ADMIN role
	 * Paths: '/users' and 'users/*' only for USER role
	 * Paths: '/adus' and 'adus/*' only for ADMIN or USER roles
	 * Login / Logout available for all
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/index")
				.permitAll()
			.antMatchers("/admin", "/admin/*")
				.hasRole("ADMIN")
			.antMatchers("/users", "/users/*")
				.hasRole("USER")
			.antMatchers("/adus/", "/adus/*")
				.hasAnyRole("ADMIN", "USER")
			.anyRequest()
				.authenticated()
			.and().formLogin()
				.permitAll()
			.and().logout()
				.permitAll();
	}
}
