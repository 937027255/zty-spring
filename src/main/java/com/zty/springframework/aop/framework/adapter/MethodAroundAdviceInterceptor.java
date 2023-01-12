package com.zty.springframework.aop.framework.adapter;

import com.zty.springframework.aop.MethodAroundAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodAroundAdviceInterceptor implements MethodInterceptor {

    MethodAroundAdvice advice;

    public MethodAroundAdviceInterceptor() {
    }

    public MethodAroundAdviceInterceptor(MethodAroundAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        this.advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        Object obj = invocation.proceed();
        this.advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());

        return obj;
    }
}
