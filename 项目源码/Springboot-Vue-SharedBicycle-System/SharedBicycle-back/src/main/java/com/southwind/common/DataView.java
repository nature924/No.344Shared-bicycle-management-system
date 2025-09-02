package com.southwind.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DataView {
    private Long count;
    private List data;
}
