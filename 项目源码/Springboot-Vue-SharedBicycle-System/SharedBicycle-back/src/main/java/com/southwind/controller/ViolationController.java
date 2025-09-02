package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.common.Cache;
import com.southwind.common.DataView;
import com.southwind.common.ViolationPageModel;
import com.southwind.entity.User;
import com.southwind.entity.Violation;
import com.southwind.mapper.UserMapper;
import com.southwind.service.ViolationService;
import com.southwind.vo.ViolationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/violation")
public class ViolationController {

    @Autowired
    private ViolationService violationService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public DataView list(ViolationPageModel violationPageModel){
        User user = (User) Cache.map.get("user");
        Page<Violation> page = new Page<>(violationPageModel.getPage(), violationPageModel.getLimit());
        QueryWrapper<Violation> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(violationPageModel.getCause()), "cause", violationPageModel.getCause())
                    .eq("uid", user.getId());
        Page<Violation> resultPage = this.violationService.page(page, queryWrapper);
        List<ViolationVO> list = new ArrayList<>();
        for (Violation record : resultPage.getRecords()) {
            ViolationVO violationVO = new ViolationVO();
            BeanUtils.copyProperties(record, violationVO);
            violationVO.setUsername(this.userMapper.getUserNameById(record.getUid()));
            list.add(violationVO);
        }
        return new DataView(resultPage.getTotal(), list);
    }

}

