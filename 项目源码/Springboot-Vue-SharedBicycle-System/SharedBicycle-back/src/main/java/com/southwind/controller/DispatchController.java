package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.common.Cache;
import com.southwind.common.DataView;
import com.southwind.common.DispatchPageModel;
import com.southwind.common.ResultObj;
import com.southwind.entity.Admin;
import com.southwind.entity.Dispatch;
import com.southwind.mapper.AdminMapper;
import com.southwind.service.DispatchService;
import com.southwind.vo.DispatchVO;
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
@RequestMapping("/dispatch")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private AdminMapper adminMapper;

    @GetMapping("/list")
    public DataView list(DispatchPageModel dispatchPageModel){
        QueryWrapper<Dispatch> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(dispatchPageModel.getContent()), "content", dispatchPageModel.getContent());
        Page<Dispatch> page = new Page<>(dispatchPageModel.getPage(), dispatchPageModel.getLimit());
        Page<Dispatch> resultPage = this.dispatchService.page(page, queryWrapper);
        List<DispatchVO> list = new ArrayList<>();
        for (Dispatch record : resultPage.getRecords()) {
            DispatchVO dispatchVO = new DispatchVO();
            BeanUtils.copyProperties(record, dispatchVO);
            dispatchVO.setUsername(this.adminMapper.getUserNameById(record.getAid()));
            list.add(dispatchVO);
        }
        return new DataView(resultPage.getTotal(), list);
    }

    @PostMapping("/add")
    public ResultObj add(@RequestBody Dispatch dispatch){
        Admin admin = (Admin) Cache.map.get("user");
        dispatch.setAid(admin.getId());
        boolean save = this.dispatchService.save(dispatch);
        if(!save) return ResultObj.DISPATCH_ADD_ERROR;
        return ResultObj.DISPATCH_ADD_SUCCESS;
    }

    @PostMapping("/update")
    public ResultObj update(@RequestBody Dispatch dispatch){
        Admin admin = (Admin) Cache.map.get("user");
        dispatch.setAid(admin.getId());
        boolean updateById = this.dispatchService.updateById(dispatch);
        if(!updateById) return ResultObj.DISPATCH_UPDATE_ERROR;
        return ResultObj.DISPATCH_UPDATE_SUCCESS;
    }

    @DeleteMapping("/delete")
    public ResultObj delete(Integer id){
        boolean removeById = this.dispatchService.removeById(id);
        if(!removeById) return ResultObj.DISPATCH_DELETE_ERROR;
        return ResultObj.DISPATCH_DELETE_SUCCESS;
    }
}

