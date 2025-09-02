package com.southwind.common;

import com.southwind.entity.Bike;
import lombok.Data;

@Data
public class BikePageModel extends Bike {
    private Integer page = 1;
    private Integer limit = 5;
}
