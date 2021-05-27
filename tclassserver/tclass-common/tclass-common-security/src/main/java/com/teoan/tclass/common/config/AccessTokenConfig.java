package com.teoan.tclass.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @author Teoan
 * @description token配置
 * @date 2021/5/25 22:16
 */
@Configuration
public class AccessTokenConfig {
    @Autowired
    DataSource dataSource;

    private String SIGNING_KEY = "teoan";

    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    @Bean
    ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean("JwtTokenServices")
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService());
        services.setSupportRefreshToken(true);
        //配置令牌存储
        services.setTokenStore(tokenStore());
        services.setTokenEnhancer(jwtAccessTokenConverter());
        return services;
    }



}
