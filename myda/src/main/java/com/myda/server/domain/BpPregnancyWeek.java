package com.myda.server.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.myda.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 孕期变化信息对象 bp_ pregnancy_week
 *
 * @author sweet-org99
 * @date 2024-03-14
 */
@Data
@TableName("bp_pregnancy_week")
public class BpPregnancyWeek implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 孕期周数
     */
    private Long week;
    /**
     * 胎儿变化
     */
    private String fetalChange;
    /**
     * 准妈妈本周情况
     */
    private String maternalChange;
    /**
     * 心理变化
     */
    private String psychologicalChange;
    /**
     * 关爱提醒
     */
    private String careTip;
    /**
     * 饮食建议
     */
    private String dietaryAdvice;
    /**
     * 常见问题
     */
    private String commonQuestion;
    /**
     * 准爸爸能做什么
     */
    private String remark;

}
