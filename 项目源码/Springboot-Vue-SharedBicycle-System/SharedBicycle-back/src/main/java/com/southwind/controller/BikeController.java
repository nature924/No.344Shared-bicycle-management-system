package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.common.BikePageModel;
import com.southwind.common.DataView;
import com.southwind.common.ResultObj;
import com.southwind.entity.Bike;
import com.southwind.mapper.AreaMapper;
import com.southwind.mapper.BikeMapper;
import com.southwind.service.BikeService;
import com.southwind.vo.BikeVO;
import com.southwind.vo.EchartsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-05-07
 */
@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    private BikeMapper bikeMapper;
    @Autowired
    private BikeService bikeService;
    @Autowired
    private AreaMapper areaMapper;

    @GetMapping("/pie")
    public List<EchartsVO> pie(){
        return this.bikeMapper.pie();
    }

    @GetMapping("/bar")
    public Map<String, List> bar(){
        List<EchartsVO> list = this.bikeMapper.bar();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (EchartsVO echartsVO : list) {
            names.add(echartsVO.getName());
            values.add(echartsVO.getValue());
        }
        Map<String,List> map = new HashMap<>();
        map.put("areaList",names);
        map.put("countList", values);
        return map;
    }

    @GetMapping("/list")
    public List<Bike> list(){
        return this.bikeService.list();
    }

    @GetMapping("/load")
    public DataView load(BikePageModel bikePageModel){
        QueryWrapper<Bike> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(bikePageModel.getNumber()), "number", bikePageModel.getNumber());
        Page<Bike> page = new Page<>(bikePageModel.getPage(), bikePageModel.getLimit());
        Page<Bike> resultPage = this.bikeService.page(page, queryWrapper);
        List<BikeVO> list = new ArrayList<>();
        for (Bike record : resultPage.getRecords()) {
            BikeVO bikeVO = new BikeVO();
            BeanUtils.copyProperties(record, bikeVO);
            bikeVO.setArea(this.areaMapper.getAreaById(record.getAid()));
            list.add(bikeVO);
        }
        return new DataView(resultPage.getTotal(), list);
    }

    @PostMapping("/add")
    public ResultObj add(@RequestBody Bike bike){
        boolean save = this.bikeService.save(bike);
        if(!save) return ResultObj.BIKE_ADD_ERROR;
        return ResultObj.BIKE_ADD_SUCCESS;
    }

    @PostMapping("/update")
    public ResultObj update(@RequestBody Bike bike){
        boolean updateById = this.bikeService.updateById(bike);
        if(!updateById) return ResultObj.BIKE_UPDATE_ERROR;
        return ResultObj.BIKE_UPDATE_SUCCESS;
    }

    @DeleteMapping("/delete")
    public ResultObj delete(Integer id){
        boolean removeById = this.bikeService.removeById(id);
        if(!removeById) return ResultObj.BIKE_DELETE_ERROR;
        return ResultObj.BIKE_DELETE_SUCCESS;
    }
}

