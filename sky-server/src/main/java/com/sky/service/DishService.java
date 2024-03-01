package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：dishService
 * @Date：2024/2/27 21:00
 * @Filename：dishService
 */
public interface DishService {
    void savewithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void deleteBatch(List<Long> ids);

    DishVO getByIdwithFlavor(Long id);

    void updatewithFlavor(DishDTO dishDTO);

    List<Dish> list(Long categoryId);
}
