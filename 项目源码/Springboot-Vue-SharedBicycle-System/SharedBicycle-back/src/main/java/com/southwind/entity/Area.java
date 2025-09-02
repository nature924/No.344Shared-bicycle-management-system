package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2024-05-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Area implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 监管人员编号
     */
      private Integer aid;

      /**
     * 停放区域
     */
      private String area;

      /**
     * 左下角经度
     */
      private Float leftlng;

      /**
     * 左下角纬度
     */
      private Float leftlat;

      /**
     * 右上角经度
     */
      private Float rightlng;

      /**
     * 右上角纬度
     */
      private Float rightlat;

      /**
     * 收费标准/小时
     */
      private Integer price;


}
