package com.southwind.vo;

import com.southwind.entity.Appeal;
import lombok.Data;

import java.util.Date;

@Data
public class AppealVO extends Appeal {
    private String username;
    private String cause;
    private Date violationTime;
}
