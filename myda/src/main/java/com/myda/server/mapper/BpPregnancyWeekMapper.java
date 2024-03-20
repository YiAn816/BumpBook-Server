package com.myda.server.mapper;

import com.myda.server.domain.BpPregnancyWeek;
import com.myda.server.domain.vo.BpPregnancyWeekVo;
import com.myda.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 孕期变化信息Mapper接口
 *
 * @author sweet-org99
 * @date 2024-03-14
 */
@Mapper
public interface BpPregnancyWeekMapper extends BaseMapperPlus<BpPregnancyWeekMapper, BpPregnancyWeek, BpPregnancyWeekVo> {

}
