package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：DishFlavorMapper
 * @Date：2024/2/27 21:18
 * @Filename：DishFlavorMapper
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);
}
