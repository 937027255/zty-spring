package cn.bugstack.springframework.mybatis;

import cn.bugstack.springframework.beans.factory.FactoryBean;
import cn.bugstack.springframework.mybatis.core.SqlSessionFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 *
 * @description 数据库操作映射工厂对象，每一个 DAO 接口对应 Mapper 的代理对象。通过代理对象完成数据库的操作。
 * @date 2022/3/18
 *
 *
 */
//每一个dao的beanDefinition都会将MapperFactoryBean作为class字段的内容，在属性填充阶段会将接口信息和sqlSessionFactory字段进行注入
//实现了FactoryBean接口，所以在调用getBean的时候实际是内部继续调用getObject的返回对象的结果
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;
    private SqlSessionFactory sqlSessionFactory;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class<T> mapperInterface, SqlSessionFactory sqlSessionFactory) {
        this.mapperInterface = mapperInterface;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            // 排除 Object 方法；toString、hashCode
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }
            try {
                System.out.println("你被代理了，执行SQL操作！" + method.getName());
                if (method.getReturnType().equals(List.class)) {
                    return sqlSessionFactory.openSession().selectList(mapperInterface.getName() + "." + method.getName(),args[0]);
                } else
                    return sqlSessionFactory.openSession().selectOne(mapperInterface.getName() + "." + method.getName(), args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return method.getReturnType().newInstance();
        };
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mapperInterface}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
