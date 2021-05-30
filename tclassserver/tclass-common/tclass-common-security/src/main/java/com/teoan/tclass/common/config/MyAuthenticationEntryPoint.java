package com.teoan.tclass.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teoan.tclass.common.result.ApiStatusCode;
import com.teoan.tclass.common.result.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Teoan
 * @description 自定义授权返回信息
 * @date 2021/5/29 15:18
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        R respBean = new R();
        respBean.setMsg(e.getMessage());
        respBean.setData("授权无效或已过期，请重新登录！");
        respBean.setCode(ApiStatusCode.ACCESS.getCode());
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
