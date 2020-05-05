//package com.util;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///**
// * 增强类
// */
//@Component
//@Aspect
//public class ServiceAdvice {
//    @Pointcut("execution(* com.service.impl.*(..))")
//    public void exec() {}
//
//    @AfterThrowing("exec()")
//    public void after() {
//
//    }
//
//}
