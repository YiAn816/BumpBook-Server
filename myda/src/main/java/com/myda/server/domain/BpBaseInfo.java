package com.myda.server.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.myda.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 档案基本信息对象 bp_base_info
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bp_base_info")
public class BpBaseInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 检查日期
     */
    private Date checkDate;
    /**
     * 所属家庭id
     */
    private Long deptId;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;

}
