package com.teoan.tclass.common.aspect;
import com.teoan.tclass.common.annotation.TclassLog;
import com.teoan.tclass.common.entity.SysUser;
import com.teoan.tclass.common.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @author Teoan
 * @description 日志切面处理
 * @date 2021/6/25 16:06
 */
@Component
@Aspect
@Slf4j
public class TclassLogAspect {

    @Autowired
    AuthUserService authUserService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.teoan.tclass.common.annotation.TclassLog)")
    public void tclassLog(){
    }


    @Before("tclassLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        SysUser currentUser = authUserService.getCurrentUser();
        //记录请求记录
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(!ObjectUtils.isEmpty(attributes)){
            HttpServletRequest request = attributes.getRequest();
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取接入点方法
        Method method = methodSignature.getMethod();

        TclassLog loggerAnnotation = method.getAnnotation(TclassLog.class);




    }

    @AfterThrowing(value = "tclassLog()",throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable throwable){

    }

}
