package cn.bugstack.springframework.mybatis;


import cn.bugstack.springframework.beans.BeansException;
import cn.bugstack.springframework.beans.PropertyValue;
import cn.bugstack.springframework.beans.PropertyValues;
import cn.bugstack.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.bugstack.springframework.beans.factory.config.BeanDefinition;
import cn.bugstack.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.bugstack.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import cn.bugstack.springframework.mybatis.core.SqlSessionFactory;
import cn.hutool.core.lang.ClassScanner;

import java.util.Set;

/**
 *
 * @description Mapper 扫描配置，根据包信息扫描接口类并注册
 * @date 2022/3/18
 *
 *
 */
//也是通过xml注册到ioc容器中，在xml中会通过指定basePackage的路径来执行所有dao接口的包扫描的路径，sqlSessionFactory对象通过在容器中注入
//postProcessBeanDefinitionRegistry方法会扫描dao包找到所有的dao接口类，并且给这些接口创建beanDefinition加入beanDefinition的map中
//beanDefintion的propertyValues中会指定接口的类和sqlSessionFactory信息并且会将MapperFactoryBean作为类信息注入
//这AbstractApplicationContext#invokeBeanFactoryPostProcessors中会对这个方法进行调用
public class MapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Set<Class<?>> classes = ClassScanner.scanPackage(basePackage);
            for (Class<?> clazz : classes) {
                // Bean 对象定义
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                PropertyValues propertyValues = new PropertyValues();
                propertyValues.addPropertyValue(new PropertyValue("mapperInterface", clazz));
                propertyValues.addPropertyValue(new PropertyValue("sqlSessionFactory", sqlSessionFactory));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setBeanClass(MapperFactoryBean.class);
                // Bean 对象注册
                registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // left intentionally blank
    }

}
