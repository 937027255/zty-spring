package com.zty.springframework.context;

import com.zty.springframework.beans.factory.HierarchicalBeanFactory;
import com.zty.springframework.beans.factory.ListableBeanFactory;
import com.zty.springframework.core.io.ResourceLoader;

/**
 *
 * @description 应用上下文接口 Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 * @date 2022/3/10
 *
 *
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher  {
}
