package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：dishController
 * @Date：2024/2/27 20:56
 * @Filename：dishController
 */
@RestController
@Api(tags = "食品")
@RequestMapping("/admin/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping
    @ApiOperation("菜品添加")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("菜品添加：{}",dishDTO);
        dishService.savewithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult =dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam List<Long> ids ){
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据id查寻菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
       DishVO dishVO=dishService.getByIdwithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品信息
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品数据")
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.updatewithFlavor(dishDTO);
        return Result.success();
    }
    @GetMapping("/list")
    @ApiOperation("根据分类id获取菜品")
    public Result<List<Dish>> list(Long categoryId){
        List<Dish> dishlist=dishService.list(categoryId);
        return Result.success(dishlist);
    }
}
