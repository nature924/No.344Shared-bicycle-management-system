package com.southwind.form;

import lombok.Data;

@Data
public class AccountForm {
    private String loginname;
    private String password;
    private Integer type;
    private String code;
}
