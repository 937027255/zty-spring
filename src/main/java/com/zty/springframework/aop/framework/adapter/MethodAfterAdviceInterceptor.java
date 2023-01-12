package com.zty.springframework.aop.framework.adapter;


import com.zty.springframework.aop.MethodAfterAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    MethodAfterAdvice advice;

    public MethodAfterAdviceInterceptor() {

    }

    public MethodAfterAdviceInterceptor(MethodAfterAdvice methodAfterAdvice) {
        this.advice = methodAfterAdvice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object res = invocation.proceed();
        this.advice.after(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        return res;
    }
}
