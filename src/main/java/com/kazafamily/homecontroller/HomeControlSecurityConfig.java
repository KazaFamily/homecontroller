package com.kazafamily.homecontroller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class HomeControlSecurityConfig extends WebSecurityConfigurerAdapter {
	    
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
			http
	    		.authorizeRequests().antMatchers("/home/others").authenticated()
	    		  .anyRequest().permitAll().and().csrf().disable();
	    }
	        
}