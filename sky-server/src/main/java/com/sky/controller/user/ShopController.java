package com.sky.controller.user;

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
@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "店铺状态")
@Slf4j
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String KEY="SHOW_STATUS";
    /**
     * 查询店铺状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取状态参数")

    public Result<Integer> getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取店铺的营业状态：{}",shopStatus==1?"营业中":"打样中");
        return Result.success(shopStatus);
    }
}
