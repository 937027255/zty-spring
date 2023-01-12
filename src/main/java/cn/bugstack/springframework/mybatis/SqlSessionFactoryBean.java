package cn.bugstack.springframework.mybatis;


import cn.bugstack.springframework.beans.factory.FactoryBean;
import cn.bugstack.springframework.beans.factory.InitializingBean;
import cn.bugstack.springframework.core.io.DefaultResourceLoader;
import cn.bugstack.springframework.core.io.Resource;
import cn.bugstack.springframework.mybatis.core.SqlSessionFactory;
import cn.bugstack.springframework.mybatis.core.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *
 * @description SqlSession 工厂
 * @date 2022/3/18
 *
 *
 */
//通过xml注册到ioc中，并指定resource的路径，实现了initializingBean方法会在初始化的阶段调用afterPropertiesSet方法
//创建出sqlSessionFactory工厂注入到属性字段中，并且实现了FactoryBean接口，最后注入到ioc中的就是sqlSessionFactory这个工厂
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    private String resource;
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(this.resource);

        try (InputStream inputStream = resource.getInputStream()) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return SqlSessionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
