package com.southwind.mapper;

import com.southwind.entity.Bike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.vo.EchartsVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2024-05-07
 */
public interface BikeMapper extends BaseMapper<Bike> {

    @Select({"select sum(time) value ,number name from operate,bike where operate.bid = bike.id group by bid"})
    public List<EchartsVO> pie();

    @Select({"select count(bike.aid) value,area name from bike,area where area.id = bike.aid group by bike.aid"})
    public List<EchartsVO> bar();

    @Select({"select number from bike where id = #{id} "})
    public String getNumberById(Integer id);

}
