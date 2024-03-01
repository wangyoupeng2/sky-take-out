package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：USerService
 * @Date：2024/3/1 19:40
 * @Filename：USerService
 */
public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
