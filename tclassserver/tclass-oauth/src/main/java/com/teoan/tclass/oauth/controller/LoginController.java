package com.teoan.tclass.oauth.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.result.ApiStatusCode;
import com.teoan.tclass.common.result.R;
import com.teoan.tclass.common.service.AuthUserService;
import com.teoan.tclass.oauth.dto.CurrentUserDTO;
import com.teoan.tclass.user.dto.StudentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

/**
 * @author Teoan
 * @description 处理登录请求
 * @date 2021/5/29 17:41
 */
@RestController
@RequestMapping("/oauth")
public class LoginController {

    //获取服务端口
    @Value("${server.port}")
    private String port;

    @Resource
    Producer producer;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    AuthUserService authUserService;

    @PostMapping("/login")
    public R login(@RequestParam Map<String,Object> map, HttpServletRequest httpServletRequest){
        MultiValueMap<String,Object> paramsMap=new LinkedMultiValueMap<>();
        String id = httpServletRequest.getSession().getId();
        String verify_code = stringRedisTemplate.opsForValue().get(httpServletRequest.getSession().getId()+"verify_code");
        if(StringUtils.isBlank(verify_code)){
            return R.failed("验证码过期");
        }
        if(verify_code.equals(map.get("code"))){
            if(map.get("grant_type").equals("password")){
                paramsMap.set("username",map.get("username"));
                paramsMap.set("password",map.get("password"));
            }else if(map.get("grant_type").equals("refresh_token")){
                paramsMap.set("refresh_token",map.get("refresh_token"));
            }
            paramsMap.set("grant_type",map.get("grant_type"));
            RestTemplate restTemplate=new RestTemplate();
            //为了安全登录时客户端id和密码不暴露在请求中 ，这里直接配置数据库中配置的值
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("webapp","123456"));
            //将请求转发到本地的请求地址
            String oauthUrl = String.format("http://localhost:%s/oauth/token",port);
            OAuth2AccessToken token=restTemplate.postForObject(oauthUrl,paramsMap,OAuth2AccessToken.class);
            if(ObjectUtils.isEmpty(token.getValue())){
                return new R(ApiStatusCode.FAILED.getCode(),"用户名或密码不正确！","登录失败");
            }
            return new R(ApiStatusCode.SUCCESS.getCode(),token,"登录成功");
        }
        return R.failed("验证码错误");
    }


    @GetMapping("/verifyCode.jpg")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();

        //使用sessionId作为key，并设置验证码过期时间
        stringRedisTemplate.opsForValue().set(session.getId()+"verify_code",text, Duration.ofSeconds(30));
        BufferedImage image = producer.createImage(text);
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }

    //获取当前登录用户信息
    @GetMapping("/getCurrentUser")
    public R getCurrentUser(){
        SysUser sysUser = authUserService.getCurrentUser();
        CurrentUserDTO currentUserDTO = new CurrentUserDTO();
        BeanUtils.copyProperties(sysUser,currentUserDTO);
        currentUserDTO.setRole(authUserService.getCurrentUserRole());
        return R.ok(currentUserDTO);
    }
}
