package com.southwind.controller;


import com.southwind.common.Cache;
import com.southwind.entity.User;
import com.southwind.mapper.UserMapper;
import com.southwind.vo.EchartsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/line")
    public Map line(){
        User user = (User) Cache.map.get("user");
        List<EchartsVO> list = this.userMapper.line(user.getId());
        List<String> dateList = new ArrayList<>();
        List<Integer> timeList = new ArrayList<>();
        for (EchartsVO echartsVO : list) {
            dateList.add(echartsVO.getName());
            timeList.add(echartsVO.getValue());
        }
        Map map = new HashMap();
        map.put("dateList",dateList);
        map.put("timeList",timeList);
        return map;
    }

}

