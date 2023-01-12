package com.zty.springframework.context.support;

import com.zty.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zty.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zty.springframework.context.ApplicationContext;

/**
 *
 * @description 抽象基类 XML 上下文 Convenient base class for {@link ApplicationContext}
 * implementations, drawing configuration from XML documents containing bean definitions
 * understood by an {@link XmlBeanDefinitionReader}.
 * @date 2022/3/10
 *
 *
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();

}
