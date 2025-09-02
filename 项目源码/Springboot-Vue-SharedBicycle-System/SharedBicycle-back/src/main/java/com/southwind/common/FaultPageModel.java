package com.southwind.common;

import com.southwind.entity.Fault;
import lombok.Data;

@Data
public class FaultPageModel extends Fault {
    private Integer page = 1;
    private Integer limit = 5;
}
