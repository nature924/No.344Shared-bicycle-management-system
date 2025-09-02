package com.southwind.vo;

import com.southwind.entity.Fault;
import lombok.Data;

@Data
public class FaultVO extends Fault {
    private String username;
    private String number;
}
