package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 根据菜品数据删除口味数据
     * @param dishId
     */
    @Delete("DELETE  from dish_flavor where dish_id=#{dishId}")
    void delete(Long dishId);

    @Select("select * from dish_flavor where dish_id=#{dishId}")
    List<DishFlavor> getById(Long dishId);
}
