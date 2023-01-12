package com.zty.springframework.test.dao;

import com.zty.springframework.test.po.User;

public interface IUserDao {

     User queryUserInfoById(Long id);

}
