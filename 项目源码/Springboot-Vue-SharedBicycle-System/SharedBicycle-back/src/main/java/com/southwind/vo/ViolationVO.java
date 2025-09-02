package com.southwind.vo;

import com.southwind.entity.Violation;
import lombok.Data;

@Data
public class ViolationVO extends Violation {
    private String username;
}
