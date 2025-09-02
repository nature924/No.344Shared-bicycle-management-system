package com.southwind.controller;


import com.southwind.common.Cache;
import com.southwind.common.ResultObj;
import com.southwind.entity.Admin;
import com.southwind.entity.Area;
import com.southwind.service.AreaService;
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
 * @since 2024-05-07
 */
@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/add")
    public ResultObj add(Area area){
        Admin admin = (Admin) Cache.map.get("user");
        area.setAid(admin.getId());
        boolean save = this.areaService.save(area);
        if(!save) return ResultObj.AREA_ADD_ERROR;
        return ResultObj.AREA_ADD_SUCCESS;
    }

    @GetMapping("/show")
    public List<Area> show(){
        return this.areaService.list();
    }

}

