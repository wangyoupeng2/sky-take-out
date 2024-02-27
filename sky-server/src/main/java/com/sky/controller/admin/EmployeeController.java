package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "登出")
    public Result<String> logout() {
        return Result.success();
    }

    @PostMapping
    @ApiOperation(value = "添加用户")
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO){
        employeeService.save(employeeDTO);
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO)
    {
        log.info("分页查询");
        PageResult pageresult = employeeService.pagrQuery(employeePageQueryDTO);
        return Result.success(pageresult);
    }

    /**
     * 启用急用员工账号
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("修改状态")
    public Result StartOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用用户账号：{},{}",status,id);
        employeeService.StartOrStop(status,id);
        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询员工信息")
    public Result<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息：{}",id);
        Employee employee=employeeService.getById(id);
        return Result.success(employee);
    }
    @PutMapping()
    @ApiOperation("编写用户信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
