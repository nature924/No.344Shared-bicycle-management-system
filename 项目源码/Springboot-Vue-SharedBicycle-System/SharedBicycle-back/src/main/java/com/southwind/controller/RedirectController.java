package com.southwind.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.common.Cache;
import com.southwind.common.ResultObj;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.form.AccountForm;
import com.southwind.service.AdminService;
import com.southwind.service.UserService;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/getCode")
    public String getCode(){
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        String code = specCaptcha.text().toLowerCase();
        Cache.map.put("code", code);
        return specCaptcha.toBase64();
    }

    @GetMapping("/login")
    public ResultObj login(AccountForm accountForm){
        //验证用户名
        if(accountForm.getType() == 4){
            //查询user
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", accountForm.getLoginname());
            User user = this.userService.getOne(queryWrapper);
            if(user == null) return ResultObj.LOGIN_ERROR_LOGINNAME;
            //验证密码
            if(!user.getPassword().equals(accountForm.getPassword())) return ResultObj.LOGIN_ERROR_PASSWORD;
            //验证code
            if(!Cache.map.get("code").toString().equals(accountForm.getCode())) return ResultObj.LOGIN_ERROR_CODE;
            Cache.map.put("type", accountForm.getType());
            Cache.map.put("user", user);
            ResultObj loginSuccess = ResultObj.LOGIN_SUCCESS;
            loginSuccess.setType(4);
            loginSuccess.setUser(user);
            return loginSuccess;
        } else {
            //查询admin
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", accountForm.getLoginname())
                        .eq("type", accountForm.getType());
            Admin admin = this.adminService.getOne(queryWrapper);
            if(admin == null) return ResultObj.LOGIN_ERROR_LOGINNAME;
            //验证密码
            if(!admin.getPassword().equals(accountForm.getPassword())) return ResultObj.LOGIN_ERROR_PASSWORD;
            //验证code
            if(!Cache.map.get("code").toString().equals(accountForm.getCode())) return ResultObj.LOGIN_ERROR_CODE;
            Cache.map.put("type", accountForm.getType());
            Cache.map.put("user", admin);
            ResultObj loginSuccess = ResultObj.LOGIN_SUCCESS;
            loginSuccess.setType(accountForm.getType());
            loginSuccess.setAdmin(admin);
            return loginSuccess;
        }
    }

}
