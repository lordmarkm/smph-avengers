package com.smph.ar.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class MobileSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("mobile")
                .password("mobilepw")
                .roles("MOBILE");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .antMatcher("/mobile/**")
                    .authorizeRequests()
                        .anyRequest().hasRole("MOBILE")
                        .and()
                .httpBasic();
        }
    }

    @Configuration
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser("admin")
                .password("123qwe")
                .roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf().disable()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/dashboard", true)
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                .and()
                .authorizeRequests()
                    .antMatchers("/db/**").hasRole("ADMIN")

                    //this one is for the .ssl verification, can comment out/delete when that's done
                    //.antMatchers("/.well-known/**").permitAll()

                    .antMatchers("/**").authenticated();
                //this stuff for the h2 console
                //.and()
                //    .headers()
                //    .frameOptions().sameOrigin();
                //end of h2 stuff
        }
    }
}
