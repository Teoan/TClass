package com.teoan.tclass.config;

import com.teoan.tclass.service.StudentService;
import com.teoan.tclass.service.impl.StudentServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Teoan
 * @description
 * @date 2020/7/27 15:22
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    StudentServiceImpl studentService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义的用户和角色数据提供者
        auth.userDetailsService(studentService).passwordEncoder(new PasswordEncoder() { //设置密码加密对象
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
        });
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("admin")///admin/**和/reg的URL都需要有超级管理员角色，如果使用.hasAuthority()方法来配置，需要在参数中加上ROLE_,如下.hasAuthority("ROLE_超级管理员")
                .anyRequest()
                .authenticated()//其他的路径都是登录后即可访问
                .and()
                .formLogin()//指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面
                .loginPage("/login_page")//设置登录页面
                .failureUrl("/login_error")//设置登录失败后跳转的页面
                .defaultSuccessUrl("/login_success",true)//设置登录成功后跳转的页面
                .loginProcessingUrl("/login")//设置登录表单提交的页面
                .usernameParameter("sid")//设置表单提交用户名时使用的参数名
                .passwordParameter("password")//设置表单提交密码时使用的参数名
                .permitAll()//允许任何人访问
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write("权限不足，请联系管理员");//返回失败json数据
                    printWriter.flush();
                    printWriter.close();
                });
    }
}
