package com.teoan.tclass.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Teoan
 * @description
 * @date 2021/5/26 14:52
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() { //设置密码加密对象
            //encode():把参数按照特定的解析规则进行解析
            @Override
            public String encode(CharSequence charSequence) {
//                使用Spring自带的加密工具加密字段
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }
            //            matches()验证从存储中获取的编码密码与编码后提交的原始密码是否匹配
//            第一个参数表示需要被解析的密码。第二个参数表示存储的密码。
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(DigestUtils.md5DigestAsHex(charSequence.toString().getBytes()));
            }
        };
    }

//    @Bean
//    MyAuthenticationProvider myAuthenticationProvider() {
//        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
//        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        myAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return myAuthenticationProvider;
//    }
//
//
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
//        ProviderManager manager = new ProviderManager(Arrays.asList(myAuthenticationProvider()));
//        return manager;
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义的用户和角色数据提供者
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/index.html", "/favicon.ico","/verifyCode.jpg");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .csrf().disable()
                .httpBasic();
    }
}
