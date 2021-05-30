package com.teoan.tclass.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teoan.tclass.common.result.ApiStatusCode;
import com.teoan.tclass.common.result.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Teoan
 * @description 自定义权限异常信息
 * @date 2021/5/29 17:11
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        R respBean = new R();
        respBean.setMsg(e.getMessage());
        respBean.setData(ApiStatusCode.ACCESS.getMsg());
        respBean.setCode(ApiStatusCode.ACCESS.getCode());
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
