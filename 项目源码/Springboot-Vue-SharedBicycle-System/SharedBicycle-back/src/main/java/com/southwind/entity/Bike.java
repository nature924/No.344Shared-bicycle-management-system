package com.southwind.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
    public class Bike implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 编号
     */
      private String number;

      /**
     * 所属区域编号
     */
      private Integer aid;

      /**
     * 位置经度

     */
      private Float lng;

      /**
     * 位置纬度
     */
      private Float lat;

      /**
     * 押金
     */
      private String deposit;

  @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
