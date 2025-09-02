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
 * @since 2024-05-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Record implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer uid;

    private Integer bid;

    private Float endlng;

    private Float endlat;

    private Float startlng;

    private Float startlat;


}
