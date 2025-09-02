package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.common.Cache;
import com.southwind.entity.Record;
import com.southwind.entity.User;
import com.southwind.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/list")
    public List<Record> list(){
        User user = (User) Cache.map.get("user");
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",user.getId());
        return this.recordService.list(queryWrapper);
    }

}

