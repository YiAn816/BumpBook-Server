package com.myda.server.domain.bo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.baomidou.mybatisplus.annotation.TableId;
import com.myda.common.core.domain.BaseEntity;
import com.myda.common.core.validate.AddGroup;
import com.myda.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 申请信息表对象 bp_dept_apply
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BpDeptApplyInfoBo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 申请状态：0-申请中，1-已同意，2-已拒绝
     */
    private Integer applyStatus;

    /**
     * 备注
     */
    private String remark;

}
