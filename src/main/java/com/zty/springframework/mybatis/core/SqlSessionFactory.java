package com.zty.springframework.mybatis.core;

/**
 *
 * @description SqlSessionFactory
 * @date 2022/3/16
 *
 *
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
