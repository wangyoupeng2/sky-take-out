package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：ShoppingCartService
 * @Date：2024/3/2 17:32
 * @Filename：ShoppingCartService
 */
public interface ShoppingCartService {
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showShoppingcart();

    void clean();
}
