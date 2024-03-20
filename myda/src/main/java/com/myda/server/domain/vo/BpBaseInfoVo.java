package com.myda.server.domain.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.myda.common.annotation.ExcelDictFormat;
import com.myda.common.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 档案基本信息视图对象 bp_base_info
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Data
@ExcelIgnoreUnannotated
public class BpBaseInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 检查日期
     */
    @ExcelProperty(value = "检查日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;

    /**
     * 所属家庭id
     */
    @ExcelProperty(value = "所属家庭id")
    private Long deptId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


    /**
     * 档案资料详细信息
     */
    private Map<String,List<BpBaseDetailInfoVo>> bpBaseDetailInfoMap;


}
