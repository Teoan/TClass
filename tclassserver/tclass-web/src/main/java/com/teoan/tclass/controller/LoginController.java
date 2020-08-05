package com.teoan.tclass.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.google.code.kaptcha.Producer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;

/**
 * @author Teoan
 * @description
 * @date 2020/7/28 16:21
 */
@RestController
public class LoginController extends ApiController {

    @Resource
    Producer producer;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/verifyCode.jpg")
    public void getVerifyCode(HttpServletResponse resp, HttpSession session) throws IOException {
        resp.setContentType("image/jpeg");
        String text = producer.createText();
//        session.setAttribute("verify_code", text);

        //使用sessionId作为key，并设置验证码过期时间
        stringRedisTemplate.opsForValue().set(session.getId()+"verify_code",text,Duration.ofSeconds(30));
        BufferedImage image = producer.createImage(text);
        try(ServletOutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }


}
