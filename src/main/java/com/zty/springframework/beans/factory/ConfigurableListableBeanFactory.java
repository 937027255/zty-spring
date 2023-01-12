package com.zty.springframework.beans.factory;

import com.zty.springframework.beans.BeansException;
import com.zty.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.zty.springframework.beans.factory.config.BeanDefinition;
import com.zty.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 *
 * @description Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 * @date 2022/3/9
 *  /CodeDesignTutorials
 *
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
