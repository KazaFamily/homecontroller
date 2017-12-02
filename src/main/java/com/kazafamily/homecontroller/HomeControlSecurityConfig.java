package com.kazafamily.homecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class HomeControlSecurityConfig extends WebSecurityConfigurerAdapter {

		private static final int REMEMBERBE_TOKEN_SECONDS = 1209600;
	
	   // @Autowired
	    //private PortalUserDetailsService userCredentialService;
	    
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	       // auth
	       //   .userDetailsService(userCredentialService)
	       //   .passwordEncoder(passwordEncoder());
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
			http
	    		.authorizeRequests()
	    		  .antMatchers("/portal/signup/**",
	    				  "/portal/1/forgotpassword",
	    				  "/portal/1/changepass").permitAll()
	    		  .antMatchers("/portal/1/**").authenticated()
	    		  .anyRequest().permitAll()
	    		.and()
	    		  .formLogin()
	    		    .usernameParameter("email")
	    		    .passwordParameter("password")
	    		    .loginProcessingUrl("/login")
	    		    .permitAll()
	    		.and()
	    			.rememberMe()
	    			.rememberMeParameter("rememberme")
	    			.key("portal")
	    			.tokenValiditySeconds(REMEMBERBE_TOKEN_SECONDS)
	    		.and()
	    		  .logout()
	    		    .logoutUrl("/logout")
	    		    .invalidateHttpSession(true)
	    		    .logoutSuccessUrl("/")
	    		    .deleteCookies("JSESSIONID")
	    		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    		.and()
	    		  .csrf().disable();
	    }
	        
}