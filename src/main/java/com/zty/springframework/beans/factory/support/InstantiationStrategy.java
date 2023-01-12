package com.zty.springframework.beans.factory.support;

import com.zty.springframework.beans.BeansException;
import com.zty.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 *
 * @description Bean 实例化策略接口
 * @date 2022/03/08
 *
 *
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
