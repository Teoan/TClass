package com.teoan.tclass.config;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teoan.tclass.entity.Student;
import com.teoan.tclass.service.StudentService;
import com.teoan.tclass.service.impl.StudentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Teoan
 * @description
 * @date 2020/7/27 15:22
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService studentDetaService;

    @Resource
    StudentService studentService;




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

    @Bean
    MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        myAuthenticationProvider.setUserDetailsService(studentDetaService);
        return myAuthenticationProvider;
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        ProviderManager manager = new ProviderManager(Arrays.asList(myAuthenticationProvider()));
        return manager;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义的用户和角色数据提供者
        auth.userDetailsService(studentDetaService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/index.html", "/favicon.ico","/verifyCode.jpg");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("admin")///admin/**URL都需要有超级管理员角色，如果使用.hasAuthority()方法来配置，需要在参数中加上ROLE_,如下.hasAuthority("ROLE_超级管理员")
                .anyRequest()
                .authenticated()//其他的路径都是登录后即可访问
                .and()
                .formLogin()//指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面
                .loginPage("/login")
                .loginProcessingUrl("/login")//设置登录表单提交的页面
                .usernameParameter("username")//设置表单提交用户名时使用的参数名
                .passwordParameter("password")//设置表单提交密码时使用的参数名
                .permitAll()//允许任何人访问
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Student student = (Student) authentication.getPrincipal();
                    studentService.updateById(Student.builder().id(student.getId()).loginTime(new Date()).build());
                    student.setPassword(null);
                    R respBean = R.ok(student);
                    respBean.setMsg("登录成功！");
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                    String s = mapper.writeValueAsString(respBean);
                    out.write(s);
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    R respBean = new R();
                    respBean.setMsg(e.getMessage());
                    if (e instanceof LockedException) {
                        respBean.setMsg("账户被锁定，请联系管理员!");
                    } else if (e instanceof CredentialsExpiredException) {
                        respBean.setMsg("密码过期，请联系管理员!");
                    } else if (e instanceof AccountExpiredException) {
                        respBean.setMsg("账户过期，请联系管理员!");
                    } else if (e instanceof DisabledException) {
                        respBean.setMsg("账户被禁用，请联系管理员!");
                    } else if (e instanceof BadCredentialsException) {
                        respBean.setMsg("用户名或者密码输入错误，请重新输入!");
                    }
                    respBean.setCode(ApiErrorCode.FAILED.getCode());
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Student student = (Student) authentication.getPrincipal();
                    R respBean = new R();
                    respBean.setMsg("注销成功！");
                    respBean.setData(student.getName());
                    respBean.setCode(ApiErrorCode.SUCCESS.getCode());
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();

                })
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(401);
                    PrintWriter out = response.getWriter();
                    R respBean = new R();
                    respBean.setData("访问失败！");
                    respBean.setCode(ApiErrorCode.FAILED.getCode());
                    if (e instanceof InsufficientAuthenticationException) {
                        respBean.setMsg("请求失败，请联系管理员!");
                    }else{
                        respBean.setMsg("尚未登录，请先登录");
                    }
                    out.write(new ObjectMapper().writeValueAsString(respBean));
                    out.flush();
                    out.close();
                });
    }
}
