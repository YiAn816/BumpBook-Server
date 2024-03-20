package com.myda.server.mapper;

import com.myda.common.core.mapper.BaseMapperPlus;
import com.myda.server.domain.BpBaseDetailInfo;
import com.myda.server.domain.BpDeptApplyInfo;
import com.myda.server.domain.vo.BpBaseDetailInfoVo;
import com.myda.server.domain.vo.BpDeptApplyInfoVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 检查档案资料详情Mapper接口
 *
 * @author sweet-org99
 * @date 2024-03-06
 */

@Mapper
public interface BpDeptApplyMapper extends BaseMapperPlus<BpDeptApplyMapper, BpDeptApplyInfo, BpDeptApplyInfoVo> {

}
