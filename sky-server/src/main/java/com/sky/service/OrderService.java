package com.sky.service;

import com.sky.dto.*;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：OrderService
 * @Date：2024/3/3 13:37
 * @Filename：OrderService
 */
public interface OrderService {
    /**
     * 用户下单接口
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);


    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 客户催单
     * @param id
     */
    void remind(Long id);

    List<Orders> historyOrders();

    /**
     * 分页查询历史订单
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /**
     * 根据id获取订单的详情
     * @param id
     * @return
     */
    OrderVO detail(Long id);

    /**
     * 再来一单
     * @param id
     */
    void repeat(Long id);

    /**
     * 分页查询订单搜索
     * @param pageQueryDTO
     * @return
     */
    PageResult pageQuery4Admin(OrdersPageQueryDTO pageQueryDTO);

    /**
     * 获取订单数量状态
     */
    OrderStatisticsVO statistic();

    /**
     * 接单
     * @param ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     * @param ordersConfirmDTO
     */
    void regect(OrdersRejectionDTO ordersConfirmDTO);

    /**
     * 取消订单
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO);

    /**
     * 派送订单
     * @param id
     */
    void delivery(Long id);

    /**
     * 完成订单
     *
     * @param id
     */
    void complete(Long id);
}
