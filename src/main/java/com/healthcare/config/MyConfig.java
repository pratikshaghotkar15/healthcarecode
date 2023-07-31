package com.healthcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {
	
	
	

	@Bean
    public  BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailServiceImpl();
		
	}
	
	
	@Bean
	 public DaoAuthenticationProvider authenticationprovider() {
		 DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		 auth.setUserDetailsService(this.getUserDetailService());
		 auth.setPasswordEncoder(passwordEncoder());
		 return auth;
		 
	 }


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationprovider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/policy/**").hasRole("USER")
		.antMatchers("/**").permitAll()
		.and().formLogin()
		.loginPage("/signin") //custom login page, username fieldename , handler url also login, view pagesource of spring security login so u can get idea
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/policy/index")
		.and().csrf().disable();
	}
	
	
	
	
	
	

}
