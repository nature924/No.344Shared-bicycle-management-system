package com.southwind.common;

import com.southwind.entity.Admin;
import com.southwind.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultObj {

    public static final ResultObj LOGIN_SUCCESS = new ResultObj(0, "登录成功");
    public static final ResultObj LOGIN_ERROR_LOGINNAME = new ResultObj(-1, "用户名或类型错误");
    public static final ResultObj LOGIN_ERROR_PASSWORD = new ResultObj(-1, "密码错误");
    public static final ResultObj LOGIN_ERROR_CODE = new ResultObj(-1, "验证码错误");

    public static final ResultObj AREA_ADD_SUCCESS = new ResultObj(0, "停车区域添加成功");
    public static final ResultObj AREA_ADD_ERROR = new ResultObj(-1, "停车区域添加失败");

    public static final ResultObj APPEAL_AUDITING_SUCCESS = new ResultObj(0, "申诉审核成功");
    public static final ResultObj APPEAL_AUDITING_ERROR = new ResultObj(-1, "申诉审核失败");

    public static final ResultObj BIKE_ADD_SUCCESS = new ResultObj(0, "单车添加成功");
    public static final ResultObj BIKE_ADD_ERROR = new ResultObj(-1, "单车添加失败");
    public static final ResultObj BIKE_UPDATE_SUCCESS = new ResultObj(0, "单车编辑成功");
    public static final ResultObj BIKE_UPDATE_ERROR = new ResultObj(-1, "单车编辑失败");
    public static final ResultObj BIKE_DELETE_SUCCESS = new ResultObj(0, "单车删除成功");
    public static final ResultObj BIKE_DELETE_ERROR = new ResultObj(-1, "单车删除失败");

    public static final ResultObj DISPATCH_ADD_SUCCESS = new ResultObj(0, "调度添加成功");
    public static final ResultObj DISPATCH_ADD_ERROR = new ResultObj(-1, "调度添加失败");
    public static final ResultObj DISPATCH_UPDATE_SUCCESS = new ResultObj(0, "调度编辑成功");
    public static final ResultObj DISPATCH_UPDATE_ERROR = new ResultObj(-1, "调度编辑失败");
    public static final ResultObj DISPATCH_DELETE_SUCCESS = new ResultObj(0, "调度删除成功");
    public static final ResultObj DISPATCH_DELETE_ERROR = new ResultObj(-1, "调度删除失败");

    public static final ResultObj FAULT_ADD_SUCCESS = new ResultObj(0, "故障上报成功");
    public static final ResultObj FAULT_ADD_ERROR = new ResultObj(-1, "故障上报失败");

    public static final ResultObj APPEAL_ADD_SUCCESS = new ResultObj(0, "申诉添加成功");
    public static final ResultObj APPEAL_ADD_ERROR = new ResultObj(-1, "申诉添加失败");

    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;
    private Integer type;
    private User user;
    private Admin admin;
}
