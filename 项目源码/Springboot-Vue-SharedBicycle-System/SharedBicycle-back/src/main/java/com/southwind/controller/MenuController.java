package com.southwind.controller;


import com.southwind.common.Cache;
import com.southwind.common.TreeNode;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.mapper.MenuMapper;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/leftMenu")
    public List<TreeNode> leftMenu(){
        //根据当前登录用户查询对应的菜单
        Integer type = (Integer) Cache.map.get("type");
        if(type == 4){
            User user = (User) Cache.map.get("user");
            return this.menuMapper.getMenuById(user.getId());
        } else {
            Admin admin = (Admin) Cache.map.get("user");
            return this.menuMapper.getMenuById(admin.getId());
        }
    }

}

