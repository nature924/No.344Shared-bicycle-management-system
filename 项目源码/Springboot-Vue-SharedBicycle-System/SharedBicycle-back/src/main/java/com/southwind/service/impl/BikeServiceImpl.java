package com.southwind.service.impl;

import com.southwind.entity.Bike;
import com.southwind.mapper.BikeMapper;
import com.southwind.service.BikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-05-07
 */
@Service
public class BikeServiceImpl extends ServiceImpl<BikeMapper, Bike> implements BikeService {

}
