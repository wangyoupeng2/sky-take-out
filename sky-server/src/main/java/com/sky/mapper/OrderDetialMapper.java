package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：OrderDetialMapper
 * @Date：2024/3/3 14:31
 * @Filename：OrderDetialMapper
 */
@Mapper
public interface OrderDetialMapper {
    void insert(List<OrderDetail> orderlist);
}
