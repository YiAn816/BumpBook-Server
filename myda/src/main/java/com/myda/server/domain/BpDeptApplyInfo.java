package com.myda.server.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myda.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 申请信息表对象 bp_dept_apply
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bp_dept_apply")
public class BpDeptApplyInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
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
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;

}
