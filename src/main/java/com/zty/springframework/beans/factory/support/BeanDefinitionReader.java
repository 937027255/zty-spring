package com.zty.springframework.beans.factory.support;

import com.zty.springframework.beans.BeansException;
import com.zty.springframework.core.io.Resource;
import com.zty.springframework.core.io.ResourceLoader;

/**
 *
 * @description Simple interface for bean definition readers.
 * @date 2022/3/9
 *
 *
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
