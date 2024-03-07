package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：ShoppingCartMapper
 * @Date：2024/3/2 17:39
 * @Filename：ShoppingCartMapper
 */
@Mapper
public interface ShoppingCartMapper {
    /**
     * 动态查询
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);
    @Update("update shopping_cart set number=#{number} where id=#{id}" )
    void updateNumberById(ShoppingCart shoppingCart1);

    /**
     *插入购物车代码
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) " +
            "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Delete("delete  from shopping_cart where user_id=#{currentId}")
    void clean(Long currentId);

    void insertBatch(List<OrderDetail> list);
}
