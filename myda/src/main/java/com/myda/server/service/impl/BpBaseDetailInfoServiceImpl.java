package com.myda.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.myda.common.core.domain.BaseEntity;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myda.system.domain.vo.SysOssVo;
import com.myda.system.service.ISysOssService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.myda.server.domain.bo.BpBaseDetailInfoBo;
import com.myda.server.domain.vo.BpBaseDetailInfoVo;
import com.myda.server.domain.BpBaseDetailInfo;
import com.myda.server.mapper.BpBaseDetailInfoMapper;
import com.myda.server.service.IBpBaseDetailInfoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 检查档案资料详情Service业务层处理
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@RequiredArgsConstructor
@Service
public class BpBaseDetailInfoServiceImpl implements IBpBaseDetailInfoService {

    private final BpBaseDetailInfoMapper baseMapper;

    private final ISysOssService iSysOssService;

    /**
     * 查询检查档案资料详情
     */
    @Override
    public BpBaseDetailInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询检查档案资料详情列表
     */
    @Override
    public TableDataInfo<BpBaseDetailInfoVo> queryPageList(BpBaseDetailInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BpBaseDetailInfo> lqw = buildQueryWrapper(bo);
        Page<BpBaseDetailInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询检查档案资料详情列表
     */
    @Override
    public List<BpBaseDetailInfoVo> queryList(BpBaseDetailInfoBo bo) {
        LambdaQueryWrapper<BpBaseDetailInfo> lqw = buildQueryWrapper(bo);
        List<BpBaseDetailInfoVo> bpBaseDetailInfoVos = baseMapper.selectVoList(lqw);
        List<SysOssVo> sysOssVos = iSysOssService.listByIds(bpBaseDetailInfoVos.stream().map(BpBaseDetailInfoVo::getFileId).collect(Collectors.toList()));
        bpBaseDetailInfoVos.forEach(s-> {
            for (SysOssVo sysOssVo : sysOssVos) {
                if(s.getFileId().equals(sysOssVo.getOssId())){
                    s.setUrl(sysOssVo.getUrl());
                }
            }
        });
        return bpBaseDetailInfoVos;
    }

    private LambdaQueryWrapper<BpBaseDetailInfo> buildQueryWrapper(BpBaseDetailInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BpBaseDetailInfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), BpBaseDetailInfo::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), BpBaseDetailInfo::getType, bo.getType());
        lqw.eq(bo.getBpBaseInfoId() != null, BpBaseDetailInfo::getBpBaseInfoId, bo.getBpBaseInfoId());
        lqw.eq(bo.getFileId() != null, BpBaseDetailInfo::getFileId, bo.getFileId());
        lqw.orderByAsc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增检查档案资料详情
     */
    @Override
    public Boolean insertByBo(BpBaseDetailInfoBo bo) {
        BpBaseDetailInfo add = BeanUtil.toBean(bo, BpBaseDetailInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改检查档案资料详情
     */
    @Override
    public Boolean updateByBo(BpBaseDetailInfoBo bo) {
        BpBaseDetailInfo update = BeanUtil.toBean(bo, BpBaseDetailInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BpBaseDetailInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除检查档案资料详情
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteBpBaseDetailInfo(Long id) {
        return baseMapper.deleteById(id) > 0;
    }
}
