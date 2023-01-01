package com.teoan.tclass.common.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.List;

/**
 * @author Teoan
 * @date 2021/5/26 14:27
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    @Value("${oauth2.resource.permit-all-paths:}")
    private String[] resourcePermitAllPaths;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore)
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置配置文件中的路径
        if (resourcePermitAllPaths.length > 0) {
            http.authorizeRequests().antMatchers(resourcePermitAllPaths).permitAll();
        }
        http.authorizeRequests().antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/swagger-resources/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/api/**/v2/api-docs",
                        "/actuator/**")
                .permitAll()
//                .antMatchers(resourcePermitAllPaths) //配置配置文件中的路径
//                .permitAll()
//                "/oauth/login",     //放行登录接口和验证码接口
//                "/oauth/verifyCode.jpg",
//                "/avatar/get/**",//放行头像接口 TODO 记录一个坑
//                "/admin/template")    //放行导入模板
                .antMatchers("/admin/**").hasRole("admin").anyRequest().authenticated();
    }
}
