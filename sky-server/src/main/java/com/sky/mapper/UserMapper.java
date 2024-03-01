package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：UserMapper
 * @Date：2024/3/1 20:00
 * @Filename：UserMapper
 */
@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid=#{openid}")
    User getByOpenid(String openid);

    /**
     * 添加注册新用户
     * @param user
     */
    void insert(User user);
}
