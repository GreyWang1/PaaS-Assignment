package com.shtel.jdbc.service;

import com.shtel.jdbc.entity.User;

import java.util.List;

/**
 * @author 王坤
 * @version 1.0.0
 * @Description
 * @date 2018/9/3
 * 版权所有 (c) 2018
 */
public interface UserService {
    /**
     * 查询所有用户
     *
     * @author 王坤
     * @Description
     * @Date 2018/9/3
     */

    List<User> findAll();

    void insertBatch();
}
