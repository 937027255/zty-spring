package com.zty.springframework.context.support;

import com.zty.springframework.beans.BeansException;
import com.zty.springframework.beans.factory.config.BeanPostProcessor;
import com.zty.springframework.context.ApplicationContext;
import com.zty.springframework.context.ApplicationContextAware;

/**
 *
 * @description 通过 BeanPostProcessor 实现类感知应用上下文对象
 * @date 2022/3/11
 *  /CodeDesignTutorials
 *
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
