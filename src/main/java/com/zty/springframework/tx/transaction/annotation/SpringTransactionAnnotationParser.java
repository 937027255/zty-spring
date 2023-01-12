package com.zty.springframework.tx.transaction.annotation;

import com.zty.springframework.core.annotation.AnnotatedElementUtils;
import com.zty.springframework.core.annotation.AnnotationAttributes;
import com.zty.springframework.tx.transaction.interceptor.RollbackRuleAttribute;
import com.zty.springframework.tx.transaction.interceptor.RuleBasedTransactionAttribute;
import com.zty.springframework.tx.transaction.interceptor.TransactionAttribute;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @description Strategy implementation for parsing Spring's {@link Transactional} annotation.
 * @date 2022/3/16
 *  /CodeDesignTutorials
 *
 */
public class SpringTransactionAnnotationParser implements TransactionAnnotationParser, Serializable {

    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement element) {
        AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(
                element, Transactional.class, false, false);
        if (null != attributes) {
            return parseTransactionAnnotation(attributes);
        } else {
            return null;
        }
    }

    /**
     * 参照源码，简化实现
     * org.springframework.transaction.annotation.SpringTransactionAnnotationParser#parseTransactionAnnotation
     */
    protected TransactionAttribute parseTransactionAnnotation(AnnotationAttributes attributes) {
        RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();

        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        for (Class<?> rbRule : attributes.getClassArray("rollbackFor")) {
            rollbackRules.add(new RollbackRuleAttribute(rbRule));
        }

        rbta.setRollbackRules(rollbackRules);
        return rbta;
    }

}
