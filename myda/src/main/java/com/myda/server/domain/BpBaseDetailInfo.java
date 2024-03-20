package com.myda.server.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.myda.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 检查档案资料详情对象 bp_base_detail_info
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bp_base_detail_info")
public class BpBaseDetailInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型：0-病历单;1-发票单;2-检查结果;3-其他
     */
    private String type;
    /**
     * 基础信息表id
     */
    private Long bpBaseInfoId;
    /**
     * 文件id
     */
    private Long fileId;
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
