package com.sky.controller.user;

import com.github.pagehelper.PageHelper;
import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.Orders;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：OrderController
 * @Date：2024/3/3 13:32
 * @Filename：OrderController
 */
@RestController("userOrderController")
@RequestMapping("/user/order")
@Api(tags = "用户端下单")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单接口
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO){
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }


    /**
     * 订单支付
     *
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        return Result.success(orderPaymentVO);
    }


    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable("id") Long id){
        orderService.remind(id);
        return Result.success();
    }


    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> page(int page, int pageSize, Integer status){
        PageResult pageResult=orderService.pageQuery4User(page,pageSize,status);
        return Result.success(pageResult);
    }
    @GetMapping("/orderDetial/{id}")
    @ApiOperation("获取订到详情")
    public Result<OrderVO> details(@PathVariable Long id){
        OrderVO order = orderService.detail(id);
/*        return Result.success(order ,m, (j,j,) findViewById(R.id.);)*/
        return Result.success(order);
    }
    @PostMapping("repetition/{id}")
    @ApiOperation("再来一单")
    public Result repeatiton(@PathVariable Long id){
        orderService.repeat(id);
        return Result.success();
    }
}
