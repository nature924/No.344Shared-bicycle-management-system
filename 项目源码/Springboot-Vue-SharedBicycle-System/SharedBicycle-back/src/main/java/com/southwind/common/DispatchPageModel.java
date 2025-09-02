package com.southwind.common;

import com.southwind.entity.Dispatch;
import lombok.Data;

@Data
public class DispatchPageModel extends Dispatch {
    private Integer page = 1;
    private Integer limit = 5;
}
