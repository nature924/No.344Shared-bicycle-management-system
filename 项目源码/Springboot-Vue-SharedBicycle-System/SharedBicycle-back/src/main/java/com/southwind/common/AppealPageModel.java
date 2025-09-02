package com.southwind.common;

import com.southwind.entity.Appeal;
import lombok.Data;

@Data
public class AppealPageModel extends Appeal {
    private Integer page = 1;
    private Integer limit = 5;
}
