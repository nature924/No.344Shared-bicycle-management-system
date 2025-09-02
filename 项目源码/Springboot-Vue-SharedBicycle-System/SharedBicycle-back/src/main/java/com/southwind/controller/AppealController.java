package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.common.AppealPageModel;
import com.southwind.common.Cache;
import com.southwind.common.DataView;
import com.southwind.common.ResultObj;
import com.southwind.entity.Appeal;
import com.southwind.entity.User;
import com.southwind.entity.Violation;
import com.southwind.form.AppealForm;
import com.southwind.service.AppealService;
import com.southwind.service.UserService;
import com.southwind.service.ViolationService;
import com.southwind.vo.AppealVO;
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
@RequestMapping("/appeal")
public class AppealController {

    @Autowired
    private AppealService appealService;
    @Autowired
    private UserService userService;
    @Autowired
    private ViolationService violationService;

    @GetMapping("/list")
    public DataView list(AppealPageModel pageModel){
        Page<Appeal> page = new Page<>(pageModel.getPage(), pageModel.getLimit());
        QueryWrapper<Appeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(pageModel.getContent()), "content", pageModel.getContent())
                    .eq(pageModel.getStatus() != null, "status",pageModel.getStatus());
        Page<Appeal> resultPage = this.appealService.page(page,queryWrapper);
        List<AppealVO> list = new ArrayList<>();
        for (Appeal record : resultPage.getRecords()) {
            AppealVO appealVO = new AppealVO();
            BeanUtils.copyProperties(record, appealVO);
            User user = this.userService.getById(record.getUid());
            appealVO.setUsername(user.getUsername());
            Violation violation = this.violationService.getById(record.getVid());
            appealVO.setCause(violation.getCause());
            appealVO.setViolationTime(violation.getViolationTime());
            list.add(appealVO);
        }
        return new DataView(resultPage.getTotal(), list);
    }

    @GetMapping("/load")
    public DataView load(AppealPageModel pageModel){
        User user = (User) Cache.map.get("user");
        Page<Appeal> page = new Page<>(pageModel.getPage(), pageModel.getLimit());
        QueryWrapper<Appeal> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(pageModel.getContent()), "content", pageModel.getContent())
                .eq(pageModel.getStatus() != null, "status",pageModel.getStatus())
                .eq("uid", user.getId());
        Page<Appeal> resultPage = this.appealService.page(page,queryWrapper);
        List<AppealVO> list = new ArrayList<>();
        for (Appeal record : resultPage.getRecords()) {
            AppealVO appealVO = new AppealVO();
            BeanUtils.copyProperties(record, appealVO);
            user = this.userService.getById(record.getUid());
            appealVO.setUsername(user.getUsername());
            Violation violation = this.violationService.getById(record.getVid());
            appealVO.setCause(violation.getCause());
            appealVO.setViolationTime(violation.getViolationTime());
            list.add(appealVO);
        }
        return new DataView(resultPage.getTotal(), list);
    }

    @PostMapping("/auditing")
    public ResultObj auditing(@RequestBody AppealForm appealForm){
        Appeal appeal = new Appeal();
        BeanUtils.copyProperties(appealForm, appeal);
        appeal.setStatus(appealForm.getAuditing());
        boolean updateById = this.appealService.updateById(appeal);
        if(!updateById) return ResultObj.APPEAL_AUDITING_ERROR;
        return ResultObj.APPEAL_AUDITING_SUCCESS;
    }

    @PostMapping("/add")
    public ResultObj add(@RequestBody Appeal appeal){
        User user = (User) Cache.map.get("user");
        appeal.setUid(user.getId());
        appeal.setStatus(0);
        boolean save = this.appealService.save(appeal);
        if(!save) return ResultObj.APPEAL_ADD_ERROR;
        return ResultObj.APPEAL_ADD_SUCCESS;
    }
}

