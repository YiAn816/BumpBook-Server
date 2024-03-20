package com.myda.server.domain.bo;

import com.myda.common.core.domain.BaseEntity;
import com.myda.common.core.validate.AddGroup;
import com.myda.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 检查档案资料详情业务对象 bp_base_detail_info
 *
 * @author sweet-org99
 * @date 2024-03-06
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BpBaseDetailInfoBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 类型：0-病历单;1-发票单;2-检查结果;3-其他
     */
    @NotBlank(message = "类型：0-病历单;1-发票单;2-检查结果;3-其他不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 基础信息表id
     */
    @NotNull(message = "基础信息表id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bpBaseInfoId;

    /**
     * 文件id
     */
    @NotNull(message = "文件id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long fileId;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
