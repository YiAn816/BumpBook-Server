package com.myda.server.domain.bo;

import com.myda.common.core.domain.BaseEntity;
import com.myda.common.core.validate.AddGroup;
import com.myda.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;


/**
 * 孕期变化信息业务对象 bp_ pregnancy_week
 *
 * @author sweet-org99
 * @date 2024-03-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BpPregnancyWeekBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 孕期周数
     */
    @NotNull(message = "孕期周数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long week;

    /**
     * 胎儿变化
     */
    @NotBlank(message = "胎儿变化不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fetalChange;

    /**
     * 准妈妈本周情况
     */
    @NotBlank(message = "准妈妈本周情况不能为空", groups = { AddGroup.class, EditGroup.class })
    private String maternalChange;

    /**
     * 心理变化
     */
    @NotBlank(message = "心理变化不能为空", groups = { AddGroup.class, EditGroup.class })
    private String psychologicalChange;

    /**
     * 关爱提醒
     */
    @NotBlank(message = "关爱提醒不能为空", groups = { AddGroup.class, EditGroup.class })
    private String careTip;

    /**
     * 饮食建议
     */
    @NotBlank(message = "饮食建议不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dietaryAdvice;

    /**
     * 常见问题
     */
    @NotBlank(message = "常见问题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String commonQuestion;

    /**
     * 准爸爸能做什么
     */
    @NotBlank(message = "准爸爸能做什么不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
