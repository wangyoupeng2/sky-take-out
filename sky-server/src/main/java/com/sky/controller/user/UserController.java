package com.sky.controller.user;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.utils.JwtUtil;
import com.sky.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：UserCOntroller
 * @Date：2024/3/1 19:37
 * @Filename：UserCOntroller
 */
@RestController
@RequestMapping("/user/user/login")
@Api(tags = "C端用户相关接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService uSerService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping
    @ApiOperation("微信登录")
    public Result<UserLoginVO> login (@RequestBody UserLoginDTO userLoginDTO){
        log.info("微信登录：{}",userLoginDTO.getCode());
        User user = uSerService.wxLogin(userLoginDTO);

        //为用户生成jwt令牌
        HashMap<String, Object> jwt = new HashMap<>();
        jwt.put(JwtClaimsConstant.USER_ID,user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),jwt);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
