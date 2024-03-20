package com.myda.server.service;

import com.myda.server.domain.BpBaseDetailInfo;
import com.myda.server.domain.vo.BpBaseDetailInfoVo;
import com.myda.server.domain.bo.BpBaseDetailInfoBo;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 检查档案资料详情Service接口
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
public interface IBpBaseDetailInfoService {

    /**
     * 查询检查档案资料详情
     */
    BpBaseDetailInfoVo queryById(Long id);

    /**
     * 查询检查档案资料详情列表
     */
    TableDataInfo<BpBaseDetailInfoVo> queryPageList(BpBaseDetailInfoBo bo, PageQuery pageQuery);

    /**
     * 查询检查档案资料详情列表
     */
    List<BpBaseDetailInfoVo> queryList(BpBaseDetailInfoBo bo);

    /**
     * 新增检查档案资料详情
     */
    Boolean insertByBo(BpBaseDetailInfoBo bo);

    /**
     * 修改检查档案资料详情
     */
    Boolean updateByBo(BpBaseDetailInfoBo bo);

    /**
     * 校验并批量删除检查档案资料详情信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 校验并批量删除档案基本信息信息
     */
    Boolean deleteBpBaseDetailInfo(Long id);
}
