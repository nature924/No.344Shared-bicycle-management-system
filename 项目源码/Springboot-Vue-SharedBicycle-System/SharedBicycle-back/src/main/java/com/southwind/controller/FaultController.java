package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.common.Cache;
import com.southwind.common.DataView;
import com.southwind.common.FaultPageModel;
import com.southwind.common.ResultObj;
import com.southwind.entity.Fault;
import com.southwind.entity.User;
import com.southwind.mapper.BikeMapper;
import com.southwind.mapper.UserMapper;
import com.southwind.service.FaultService;
import com.southwind.vo.FaultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-05-07
 */
@RestController
@RequestMapping("/fault")
public class FaultController {

    @Autowired
    private FaultService faultService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BikeMapper bikeMapper;

    @GetMapping("/list")
    public DataView list(FaultPageModel faultPageModel){
        QueryWrapper<Fault> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(faultPageModel.getReason()), "reason",faultPageModel.getReason());
        Page<Fault> page = new Page<>(faultPageModel.getPage(), faultPageModel.getLimit());
        Page<Fault> resultPage = this.faultService.page(page,queryWrapper);
        List<FaultVO> list = new ArrayList<>();
        for (Fault record : resultPage.getRecords()) {
            FaultVO faultVO = new FaultVO();
            BeanUtils.copyProperties(record, faultVO);
            faultVO.setUsername(this.userMapper.getUserNameById(record.getUid()));
            faultVO.setNumber(this.bikeMapper.getNumberById(record.getBid()));
            list.add(faultVO);
        }
        return new DataView(resultPage.getTotal(), list);
    }

    @PostMapping("/add")
    public ResultObj add(@RequestBody Fault fault){
        User user = (User) Cache.map.get("user");
        fault.setUid(user.getId());
        boolean save = this.faultService.save(fault);
        if(!save) return ResultObj.FAULT_ADD_ERROR;
        return ResultObj.FAULT_ADD_SUCCESS;
    }
}

