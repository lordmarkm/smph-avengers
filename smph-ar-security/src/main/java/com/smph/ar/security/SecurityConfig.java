package com.smph.ar.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author mbmartinez
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Override
    public void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
 
        //auth.authenticationProvider(realAuthProvider);
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("123qwe")
            .roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .httpBasic()
            .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                .usernameParameter("username")
                .passwordParameter("password")
            .and()
                .logout()
                .logoutUrl("/logout")
            .and()
                .authorizeRequests()
                .antMatchers("/db/**").permitAll()
                .antMatchers(HttpMethod.GET, "/mobile/**").permitAll()
                .antMatchers("/**").authenticated()

            //this stuff for the h2 console
            .and()
                .headers()
                .frameOptions().sameOrigin();
            //end of h2 stuff
    }

}