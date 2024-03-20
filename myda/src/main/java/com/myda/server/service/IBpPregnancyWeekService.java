package com.myda.server.service;

import com.myda.server.domain.BpPregnancyWeek;
import com.myda.server.domain.vo.BpPregnancyWeekVo;
import com.myda.server.domain.bo.BpPregnancyWeekBo;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 孕期变化信息Service接口
 *
 * @author sweet-org99
 * @date 2024-03-14
 */
public interface IBpPregnancyWeekService {

    /**
     * 查询孕期变化信息
     */
    BpPregnancyWeekVo queryById(Long id);

    /**
     * 根据周数查询孕期变化信息
     * @param week
     * @return
     */
    BpPregnancyWeekVo queryByWeek(Integer week);

    /**
     * 查询孕期变化信息列表
     */
    TableDataInfo<BpPregnancyWeekVo> queryPageList(BpPregnancyWeekBo bo, PageQuery pageQuery);

    /**
     * 查询孕期变化信息列表
     */
    List<BpPregnancyWeekVo> queryList(BpPregnancyWeekBo bo);

    /**
     * 新增孕期变化信息
     */
    Boolean insertByBo(BpPregnancyWeekBo bo);

    /**
     * 修改孕期变化信息
     */
    Boolean updateByBo(BpPregnancyWeekBo bo);

    /**
     * 校验并批量删除孕期变化信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
