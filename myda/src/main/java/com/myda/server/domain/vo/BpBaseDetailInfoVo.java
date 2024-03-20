package com.myda.server.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.myda.common.annotation.ExcelDictFormat;
import com.myda.common.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 检查档案资料详情视图对象 bp_base_detail_info
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Data
@ExcelIgnoreUnannotated
public class BpBaseDetailInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 类型：0-病历单;1-发票单;2-检查结果;3-其他
     */
    @ExcelProperty(value = "类型：0-病历单;1-发票单;2-检查结果;3-其他")
    private String type;

    /**
     * 基础信息表id
     */
    @ExcelProperty(value = "基础信息表id")
    private Long bpBaseInfoId;

    /**
     * 文件id
     */
    @ExcelProperty(value = "文件id")
    private Long fileId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 图片url
     */
    private String url;


}
