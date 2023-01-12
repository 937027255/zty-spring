package com.zty.springframework.context.support;

import com.zty.springframework.beans.BeansException;
import com.zty.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zty.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zty.springframework.context.ApplicationContext;

/**
 *
 * @description 抽象基类刷新应用上下文 Base class for {@link ApplicationContext}
 * implementations which are supposed to support multiple calls to {@link #refresh()},
 * creating a new internal bean factory instance every time.
 * Typically (but not necessarily), such a context will be driven by
 * a set of config locations to load bean definitions from.
 * @date 2022/3/10
 *
 *
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
