package com.zty.springframework.context.support;

import com.zty.springframework.beans.factory.FactoryBean;
import com.zty.springframework.beans.factory.InitializingBean;
import com.zty.springframework.core.convert.ConversionService;
import com.zty.springframework.core.convert.converter.Converter;
import com.zty.springframework.core.convert.converter.ConverterFactory;
import com.zty.springframework.core.convert.converter.ConverterRegistry;
import com.zty.springframework.core.convert.converter.GenericConverter;
import com.zty.springframework.core.convert.support.DefaultConversionService;
import com.zty.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 *
 * @description 提供创建 ConversionService 工厂
 * @date 2022/3/16
 *
 *
 */
//这个类需要在xml中注册才能使用
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    private Set<?> converters;//xml中进行注入操作

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

}
