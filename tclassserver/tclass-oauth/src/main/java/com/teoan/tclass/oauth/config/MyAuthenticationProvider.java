package com.teoan.tclass.oauth.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Teoan
 * @description 自定义字段验证
 * @date 2021/5/26 14:55
 */
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String code = req.getParameter("code");

//        String verify_code = (String) req.getSession().getAttribute("verify_code");
        String verify_code =stringRedisTemplate.opsForValue().get(req.getSession().getId()+"verify_code");

        if (verify_code == null){
            throw new AuthenticationServiceException("验证码过期");
        }
        if (code == null || !code.equals(verify_code)) {
            throw new AuthenticationServiceException("验证码错误");
        }
        super.additionalAuthenticationChecks(userDetails, authentication);

    }
}
