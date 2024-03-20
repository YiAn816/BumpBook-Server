package com.myda.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.myda.server.domain.bo.BpPregnancyWeekBo;
import com.myda.server.domain.vo.BpPregnancyWeekVo;
import com.myda.server.domain.BpPregnancyWeek;
import com.myda.server.mapper.BpPregnancyWeekMapper;
import com.myda.server.service.IBpPregnancyWeekService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 孕期变化信息Service业务层处理
 *
 * @author sweet-org99
 * @date 2024-03-14
 */
@RequiredArgsConstructor
@Service
public class BpPregnancyWeekServiceImpl implements IBpPregnancyWeekService {

    private final BpPregnancyWeekMapper baseMapper;

    /**
     * 查询孕期变化信息
     */
    @Override
    public BpPregnancyWeekVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public BpPregnancyWeekVo queryByWeek(Integer week) {
        LambdaQueryWrapper<BpPregnancyWeek> lqw = Wrappers.lambdaQuery();
        lqw.eq(BpPregnancyWeek::getWeek, week);
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * 查询孕期变化信息列表
     */
    @Override
    public TableDataInfo<BpPregnancyWeekVo> queryPageList(BpPregnancyWeekBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BpPregnancyWeek> lqw = buildQueryWrapper(bo);
        Page<BpPregnancyWeekVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询孕期变化信息列表
     */
    @Override
    public List<BpPregnancyWeekVo> queryList(BpPregnancyWeekBo bo) {
        LambdaQueryWrapper<BpPregnancyWeek> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BpPregnancyWeek> buildQueryWrapper(BpPregnancyWeekBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BpPregnancyWeek> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getWeek() != null, BpPregnancyWeek::getWeek, bo.getWeek());
        lqw.eq(StringUtils.isNotBlank(bo.getFetalChange()), BpPregnancyWeek::getFetalChange, bo.getFetalChange());
        lqw.eq(StringUtils.isNotBlank(bo.getMaternalChange()), BpPregnancyWeek::getMaternalChange, bo.getMaternalChange());
        lqw.eq(StringUtils.isNotBlank(bo.getPsychologicalChange()), BpPregnancyWeek::getPsychologicalChange, bo.getPsychologicalChange());
        lqw.eq(StringUtils.isNotBlank(bo.getCareTip()), BpPregnancyWeek::getCareTip, bo.getCareTip());
        lqw.eq(StringUtils.isNotBlank(bo.getDietaryAdvice()), BpPregnancyWeek::getDietaryAdvice, bo.getDietaryAdvice());
        lqw.eq(StringUtils.isNotBlank(bo.getCommonQuestion()), BpPregnancyWeek::getCommonQuestion, bo.getCommonQuestion());
        return lqw;
    }

    /**
     * 新增孕期变化信息
     */
    @Override
    public Boolean insertByBo(BpPregnancyWeekBo bo) {
        BpPregnancyWeek add = BeanUtil.toBean(bo, BpPregnancyWeek.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改孕期变化信息
     */
    @Override
    public Boolean updateByBo(BpPregnancyWeekBo bo) {
        BpPregnancyWeek update = BeanUtil.toBean(bo, BpPregnancyWeek.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BpPregnancyWeek entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除孕期变化信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
