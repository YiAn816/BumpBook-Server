package com.myda.server.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ccQi
 * @Package com.myda.server.domain.vo
 * @Description: TODO
 * @date
 */

@Data
public class PregnancyInfo {


    @JsonFormat(pattern ="yyyy-MM-dd", timezone = "GMT+8")
    private Date dueDate;

    /**
     * 孕期类型 0-孕初期，1-孕中期，2-孕晚期
     */
    private Integer pregnancyType;

    /**
     * 孕期天数
     */
    private Integer days;

    /**
     * 孕期周数
     */
    private Integer weeks;
}
