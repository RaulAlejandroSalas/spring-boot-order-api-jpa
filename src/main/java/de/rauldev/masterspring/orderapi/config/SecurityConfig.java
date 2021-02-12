package de.rauldev.masterspring.orderapi.config;

import de.rauldev.masterspring.orderapi.securiry.RestAuthenticationEntryPoint;
import de.rauldev.masterspring.orderapi.securiry.TokenAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public TokenAuthenticationFilter createTokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
             .and()
             .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
             .csrf()
             .disable()
             .formLogin()
             .disable()
             .httpBasic()
             .disable()
             .exceptionHandling()
             .authenticationEntryPoint(new RestAuthenticationEntryPoint()) //Authentication Errors Management
             .and()
             .authorizeRequests()
             .antMatchers("/login", "/signup")
             .permitAll()
             .anyRequest()
             .authenticated();
        
        http.addFilterBefore(createTokenAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }
}
