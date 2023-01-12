package com.zty.springframework.util;

import com.zty.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 *
 * @description Simple strategy interface for resolving a String value.
 * Used by {@link ConfigurableBeanFactory}.
 * @date 2022/3/9
 *  /CodeDesignTutorials
 *
 */
public interface StringValueResolver {

    String resolveStringValue(String strVal);

}
