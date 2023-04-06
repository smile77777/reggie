package com.example.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.reggie.common.BaseContext;
import com.example.reggie.common.R;
import com.example.reggie.pojo.Employee;
import com.example.reggie.pojo.Orders;
import com.example.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        orderService.submit(orders);
        return R.success("提交成功!");
    }

    @GetMapping("/userPage")
    public R<Page> userPage(int page,int pageSize){
        log.info("page={},pageSize={},name={}",page,pageSize);

        Page pageInfo=new Page(page,pageSize);

        Long userId = BaseContext.getCurrentId();

        //添加过滤条件
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.eq(Orders::getUserId,userId);
        queryWrapper.orderByDesc(Orders::getOrderTime);

        orderService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }
}
