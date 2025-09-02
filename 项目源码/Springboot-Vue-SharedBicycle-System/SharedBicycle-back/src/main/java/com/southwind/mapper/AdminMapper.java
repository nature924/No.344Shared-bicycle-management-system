package com.southwind.mapper;

import com.southwind.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-05-07
 */
public interface AdminMapper extends BaseMapper<Admin> {

    @Select({"select username from admin where id = #{id} "})
    public String getUserNameById(Integer id);
}
