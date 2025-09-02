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
    public class Fault implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 调度人员编号
     */
      private Integer uid;

      /**
     * 单车编号
     */
      private Integer bid;

      /**
     * 故障原因
     */
      private String reason;

      /**
     * 车辆停放经度
     */
      private Float lng;

      /**
     * 车辆停放纬度
     */
      private Float lat;

  @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
