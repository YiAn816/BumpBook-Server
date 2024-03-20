package com.myda.server.mapper;

import com.myda.server.domain.BpBaseInfo;
import com.myda.server.domain.vo.BpBaseInfoVo;
import com.myda.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 档案基本信息Mapper接口
 *
 * @author sweet-org99
 * @date 2024-03-06
 */

@Mapper
public interface BpBaseInfoMapper extends BaseMapperPlus<BpBaseInfoMapper, BpBaseInfo, BpBaseInfoVo> {

}
