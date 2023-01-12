package com.zty.springframework.test;

import com.zty.springframework.beans.factory.BeanFactory;
import com.zty.springframework.context.support.ClassPathXmlApplicationContext;
import com.zty.springframework.test.dao.IUserDao;
import com.zty.springframework.test.po.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 *
 * @description 单元测试
 * @date 2022/3/16
 *
 *
 */
public class ApiTest {

    @Test
    public void test_ClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserDao userDao = beanFactory.getBean("IUserDao", IUserDao.class);
        User user = userDao.queryUserInfoById(1L);
        System.out.println("测试结果：" + JSON.toJSONString(user));
    }

}
