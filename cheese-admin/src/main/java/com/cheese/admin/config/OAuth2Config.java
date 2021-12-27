package com.cheese.admin.config;

import com.cheese.admin.advice.RestExceptionHandler;
import com.cheese.admin.exception.CustomAuthExceptionHandler;
import com.cheese.admin.service.AdminDetailsServiceImpl;
import com.cheese.core.exception.CheeseCode;
import com.cheese.core.model.response.CheeseErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


import javax.sql.DataSource;


@Configuration
public class OAuth2Config {
    @Configuration
    @EnableResourceServer
    protected static class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter
    {
        @Autowired
        private CustomAuthExceptionHandler customAuthExceptionHandler;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.stateless(false)
                    .accessDeniedHandler(customAuthExceptionHandler)
                    .authenticationEntryPoint(customAuthExceptionHandler);
        }


        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .anonymous().disable()
                    .authorizeRequests().anyRequest().authenticated();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private AdminDetailsServiceImpl adminDetailsService;

        @Autowired
        private DataSource ds;
        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.checkTokenAccess("permitAll()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(ds);
        }
//
//        @Bean
//        public WebResponseExceptionTranslator webResponseExceptionTranslator() {
//            return new DefaultWebResponseExceptionTranslator() {
//
//                @Override
//                public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//                    // ==================== never gets called ===============
//                    ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
//                    OAuth2Exception body = responseEntity.getBody();
//                    CheeseCode cheeseCode = CheeseCode.INVALID_GRANT;
//                    CheeseErrorResponse res = CheeseErrorResponse.builder()
//                            .message(cheeseCode.getMessage())
//                            .code(cheeseCode.getCode())
//                            .status(cheeseCode.getStatus())
//                            .build();
//                    HttpHeaders headers = new HttpHeaders();
//                    headers.setAll(responseEntity.getHeaders().toSingleValueMap());
//                    // do something with header or response
//                    System.out.println(headers);
//                    System.out.println("========================== in webResponseExceptionTranslator  ===============================");
//                    return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
//                }
//            };
//        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
//                    .exceptionTranslator(webResponseExceptionTranslator())
                    .tokenStore(tokenStore())
                    .authenticationManager(authenticationManager)
                    .userDetailsService(adminDetailsService);
        }

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(ds);
        }

    }

}


