package com.zty.springframework.beans.factory.annotation;

import com.zty.springframework.beans.BeansException;
import com.zty.springframework.beans.PropertyValues;
import com.zty.springframework.beans.factory.BeanFactory;
import com.zty.springframework.beans.factory.BeanFactoryAware;
import com.zty.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zty.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.zty.springframework.util.ClassUtils;
import cn.hutool.core.bean.BeanUtil;
import com.zty.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 *
 * @description 处理 @Value、@Autowired，注解的 BeanPostProcessor {@link BeanPostProcessor} implementation
 * that autowires annotated fields, setter methods and arbitrary config methods.
 * Such members to be injected are detected through a Java 5 annotation: by default,
 * Spring's {@link Autowired @Autowired} and {@link Value @Value} annotations.
 * @date 2022/3/15
 *  /CodeDesignTutorials
 *
 */
//会在包扫描的逻辑中手动将这个类加入到beanDefinition中
//postProcessPropetyValues逻辑会在doCreate#applyBeanPostProcessorsBeforeApplyingPropertyValues这个方法中进行调用
//调用时机在对象实例化之后并且进行beanDefinition#applyPropertyValues属性填充之前完成对@Value和@AutoWired的注解扫描填充逻辑
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 1. 处理注解 @Value
        Class<?> clazz = bean.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2. 处理注解 @Autowired
        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }

        return pvs;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
