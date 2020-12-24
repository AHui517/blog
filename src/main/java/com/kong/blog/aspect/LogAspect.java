package com.kong.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
//切面注解
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());
//    生明切面注解
    @Pointcut("execution(* com.kong.blog.controller.*.*(..))")
    public void log(){

    }

//   日志信息类
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

    @Override
    public String toString() {
        return "RequestLog{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }

    public RequestLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }
}
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("request : {}",requestLog);

    }
    @After("log()")
    public void doAfter(){
        logger.info("---after---");
    }
    @AfterReturning(returning = "obj",pointcut = "log()")
    public void doAfterReturn(Object obj){
        logger.info("result : {}"+obj);
    }

}
