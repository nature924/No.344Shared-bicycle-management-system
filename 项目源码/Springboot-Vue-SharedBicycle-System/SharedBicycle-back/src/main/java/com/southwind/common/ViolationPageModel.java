package com.southwind.common;

import com.southwind.entity.Violation;
import lombok.Data;

@Data
public class ViolationPageModel extends Violation {
    private Integer page = 1;
    private Integer limit = 5;
}
