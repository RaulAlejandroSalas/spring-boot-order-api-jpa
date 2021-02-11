package de.rauldev.masterspring.orderapi.config;

import de.rauldev.masterspring.orderapi.securiry.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
    }
}
