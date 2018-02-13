package com.rt.jdbc_bcrypt_pass;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityJdbcHandler {
	
	/**
	 * Datasource info taken from application.properties file
	 */
	@Autowired
	DataSource dataSource;
	
	/**
	 * Use BCrypt for password hashing
	 * See also: https://codahale.com/how-to-safely-store-a-password/
	 */
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	/** JDBC users and authorities */
	@Bean
	public UserDetailsService userDetailsService() {
		JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
		jdbcDaoImpl.setDataSource(dataSource);
		jdbcDaoImpl.setUsersByUsernameQuery("select username,password,enabled from users where username=?");
		jdbcDaoImpl.setAuthoritiesByUsernameQuery(""
				+ "select users.username, roles.role "
				+ "from roles, user_roles, users "
				+ "where user_roles.role_id=roles.role_id "
				+ "and users.user_id=user_roles.user_id "
				+ "and users.username=?");
		return jdbcDaoImpl;
	}

}
