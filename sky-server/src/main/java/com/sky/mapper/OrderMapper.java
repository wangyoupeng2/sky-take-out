package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：OrderMapper
 * @Date：2024/3/3 14:31
 * @Filename：OrderMapper
 */
@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    @Update("update orders set status = #{orderStatus},pay_status = #{orderPaidStatus} ,checkout_time = #{check_out_time} where id = #{id}")
    void updateStatus(Integer orderStatus, Integer orderPaidStatus, LocalDateTime check_out_time, Long id);

    @Select("SELECT * FROM orders WHERE status = #{status} and checkout_time < #{localDateTime}")
    List<Orders> getBystatusAndTime(Integer status,LocalDateTime localDateTime);

    @Select("SELECT * FROM orders where id=#{id}")
    Orders getById(Long id);

    @Select("SELECT * from orders where user_id=#{id}")
    List<Orders> historyOrders(Long id);

    Page<Orders> pageQuery(OrdersPageQueryDTO dto);

    @Select("select count(*) from orders where status=#{status}")
    Integer CountStatus(Integer status);

    Double sumByMap(Map map);
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);

    Integer countByMap(Map map);
}
