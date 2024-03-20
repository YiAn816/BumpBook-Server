package com.myda.server.domain.bo;

import com.myda.common.core.domain.BaseEntity;
import com.myda.common.core.validate.AddGroup;
import com.myda.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 档案基本信息业务对象 bp_base_info
 *
 * @author sweet-org99
 * @date 2024-03-06
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BpBaseInfoBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 检查日期
     */
    @NotNull(message = "检查日期不能为空", groups = { AddGroup.class, EditGroup.class })
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;

    /**
     * 所属家庭id
     */
    private Long deptId;

    /**
     * 备注
     */
    private String remark;


}
