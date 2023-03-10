package com.zty.springframework.aop.framework.adapter;

import com.zty.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *
 * @description Interceptor to wrap am {@link com.zty.springframework.aop.MethodBeforeAdvice}.
 * Used internally by the AOP framework; application developers should not need
 * to use this class directly.
 * @date 2022/3/14
 *  /CodeDesignTutorials
 *
 */

/**
 * 内部的拦截器，这个也需要从xml注册到容器中
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }

}
