package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：ShopController
 * @Date：2024/2/29 20:41
 * @Filename：ShopController
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺状态")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String KEY="SHOW_STATUS";
    /**
     * 设置店铺状态
     * @param status
     * @return
     */
    @PutMapping("{status}")
    @ApiOperation("设置店铺状态")
    public Result setStatus(@PathVariable Integer status){
       log.info("设置店铺状态：{}",status==1?"营业中":"打样中");
        redisTemplate.opsForValue().set(KEY,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取状态参数")

    public Result<Integer> getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺的营业状态：{}",shopStatus==1?"营业中":"打样中");
        return Result.success(shopStatus);
    }
}
