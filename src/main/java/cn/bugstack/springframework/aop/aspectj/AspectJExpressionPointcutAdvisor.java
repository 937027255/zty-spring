package cn.bugstack.springframework.aop.aspectj;

import cn.bugstack.springframework.aop.Pointcut;
import cn.bugstack.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 *
 * @description Spring AOP Advisor that can be used for any AspectJ pointcut expression.
 * @date 2022/3/14
 *  /CodeDesignTutorials
 *
 */
//通知类需要在xml中进行注册,一个通知类包装了一个MethodBeforeAdviceInterceptor，MethodBeforeAdviceInterceptor中包含具体的通知
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;//就是一个Interceptor
    // 表达式
    private String expression;//一个通知对应一个切点表达式

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}

