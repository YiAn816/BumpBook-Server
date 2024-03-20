package com.myda.server.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.myda.common.annotation.ExcelDictFormat;
import com.myda.common.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 孕期变化信息视图对象 bp_ pregnancy_week
 *
 * @author sweet-org99
 * @date 2024-03-14
 */
@Data
@ExcelIgnoreUnannotated
public class BpPregnancyWeekVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 孕期周数
     */
    @ExcelProperty(value = "孕期周数")
    private Long week;

    /**
     * 胎儿变化
     */
    @ExcelProperty(value = "胎儿变化")
    private String fetalChange;

    /**
     * 准妈妈本周情况
     */
    @ExcelProperty(value = "准妈妈本周情况")
    private String maternalChange;

    /**
     * 心理变化
     */
    @ExcelProperty(value = "心理变化")
    private String psychologicalChange;

    /**
     * 关爱提醒
     */
    @ExcelProperty(value = "关爱提醒")
    private String careTip;

    /**
     * 饮食建议
     */
    @ExcelProperty(value = "饮食建议")
    private String dietaryAdvice;

    /**
     * 常见问题
     */
    @ExcelProperty(value = "常见问题")
    private String commonQuestion;

    /**
     * 准爸爸能做什么
     */
    @ExcelProperty(value = "准爸爸能做什么")
    private String remark;


}
