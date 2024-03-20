package com.myda.server.service;

import com.myda.common.core.domain.PageQuery;
import com.myda.common.core.page.TableDataInfo;
import com.myda.server.domain.bo.BpDeptApplyInfoBo;
import com.myda.server.domain.bo.BpDeptApplyInfoBo;
import com.myda.server.domain.vo.BpBaseDetailInfoVo;
import com.myda.server.domain.vo.BpDeptApplyInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 申请信息表Service接口
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
public interface IBpDeptApplyService {

    /**
     * 查询申请信息表
     */
    BpDeptApplyInfoVo queryById(Long id);

    /**
     * 查询申请信息表列表
     */
    TableDataInfo<BpDeptApplyInfoVo> queryPageList(BpDeptApplyInfoBo bo, PageQuery pageQuery);

    /**
     * 查询申请信息表列表
     */
    List<BpDeptApplyInfoVo> queryList(BpDeptApplyInfoBo bo);

    /**
     * 新增申请信息表
     */
    Boolean insertByBo(BpDeptApplyInfoBo bo);

    /**
     * 修改申请信息表
     */
    Boolean updateByBo(BpDeptApplyInfoBo bo);

    /**
     * 校验并批量删除申请信息表信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);


    /**
     * 申请加入家庭
     * @param deptId
     * @param userId
     * @param remark
     * @return
     */
    Boolean applyJoinDept(Long deptId, Long userId, String remark);
}
