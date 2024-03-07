package com.sky.task;

import com.sky.context.BaseContext;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：MyTask
 * @Date：2024/3/4 20:05
 * @Filename：MyTask
 * 自定义时间任务类
 */
@Component
@Slf4j
public class MyTask {

    @Autowired
    private OrderMapper orderMapper;
    /**
     * 定时任务
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeOutTask(){
        log.info("清楚超时支付订单:{}",new Date());
        LocalDateTime orderTime = LocalDateTime.now().plusMinutes(-15);
        List<Orders> orderlist = orderMapper.getBystatusAndTime(Orders.PENDING_PAYMENT,orderTime);

        if(orderlist!=null && orderlist.size()>0){
            for (Orders order : orderlist) {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时,自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliverOrder(){
        log.info("定时处理快递超时订单:{}",LocalDateTime.now());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> ordersList = orderMapper.getBystatusAndTime(Orders.DELIVERY_IN_PROGRESS, time);

        if(ordersList!=null && ordersList.size()>0){
            for (Orders order : ordersList) {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            }
        }
    }
}
