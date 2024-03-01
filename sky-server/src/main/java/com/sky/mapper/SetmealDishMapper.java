package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：SetmealDishMapper
 * @Date：2024/2/28 20:36
 * @Filename：SetmealDishMapper
 */
@Mapper
public interface SetmealDishMapper {

    List<Long> getSetmealByDishId(List<Long> dishIds);

    void inserBatch(List<SetmealDish> setmealDishs);

    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long setmealId);

    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> getBySetmealId(Long id);
}
