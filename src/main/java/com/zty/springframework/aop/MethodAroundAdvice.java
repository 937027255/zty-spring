package com.zty.springframework.aop;

import java.lang.reflect.Method;

public interface MethodAroundAdvice extends AroundAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;

    void after(Method method, Object[] args, Object target) throws Throwable;

}
