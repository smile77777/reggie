package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.dto.DishDto;
import com.example.reggie.pojo.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWhitFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);

    public void deleteWithStatus(List<Long> ids);
}
