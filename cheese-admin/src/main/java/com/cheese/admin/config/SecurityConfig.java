package com.cheese.admin.config;

import com.cheese.admin.service.AdminDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
@Order(1)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AdminDetailsServiceImpl adminDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO: VEREIFY - Does this simply mean ALL endpoints are authenticated?
        //Should we list out the /login and /oauth/authorize endpoints specifically, then?
        http
                .requestMatchers()
                .antMatchers( "/oauth/authorize")
                .and().authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(adminDetailsService);
        return provider;
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter c = new CommonsRequestLoggingFilter();
        c.setIncludeHeaders(true);
        c.setIncludeQueryString(true);
        c.setIncludePayload(true);
        c.setIncludeClientInfo(true);
        c.setMaxPayloadLength(100000);
        return c;
    }

}