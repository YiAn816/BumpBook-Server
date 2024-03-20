package com.myda.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myda.common.core.domain.PageQuery;
import com.myda.common.core.domain.model.LoginUser;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.exception.ServiceException;
import com.myda.common.helper.LoginHelper;
import com.myda.server.domain.BpBaseInfo;
import com.myda.server.domain.bo.BpBaseDetailInfoBo;
import com.myda.server.domain.bo.BpBaseInfoBo;
import com.myda.server.domain.vo.BpBaseDetailInfoVo;
import com.myda.server.domain.vo.BpBaseInfoVo;
import com.myda.server.mapper.BpBaseInfoMapper;
import com.myda.server.service.IBpBaseDetailInfoService;
import com.myda.server.service.IBpBaseInfoService;
import com.myda.system.domain.vo.SysOssVo;
import com.myda.system.service.ISysOssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 档案基本信息Service业务层处理
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BpBaseInfoServiceImpl implements IBpBaseInfoService {

    private final BpBaseInfoMapper baseMapper;

    private final ISysOssService iSysOssService;

    private final IBpBaseDetailInfoService iBpBaseDetailInfoService;

    /**
     * 查询档案基本信息
     */
    @Override
    public BpBaseInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询档案基本信息列表
     */
    @Override
    public TableDataInfo<BpBaseInfoVo> queryPageList(BpBaseInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BpBaseInfo> lqw = buildQueryWrapper(bo);
        Page<BpBaseInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询档案基本信息列表
     */
    @Override
    public List<BpBaseInfoVo> queryList(BpBaseInfoBo bo) {
        LambdaQueryWrapper<BpBaseInfo> lqw = buildQueryWrapper(bo);
        lqw.orderByDesc(BpBaseInfo::getCheckDate);
        List<BpBaseInfoVo> bpBaseInfoVos = baseMapper.selectVoList(lqw);
        bpBaseInfoVos.forEach(bpBaseInfoVo -> {
            BpBaseDetailInfoBo query = new BpBaseDetailInfoBo();
            query.setBpBaseInfoId(bpBaseInfoVo.getId());
            List<BpBaseDetailInfoVo> bpBaseDetailInfoVos = this.iBpBaseDetailInfoService.queryList(query);
            bpBaseInfoVo.setBpBaseDetailInfoMap(bpBaseDetailInfoVos.stream()
                .collect(Collectors.groupingBy(BpBaseDetailInfoVo::getType)));
        });
        return bpBaseInfoVos;
    }

    private LambdaQueryWrapper<BpBaseInfo> buildQueryWrapper(BpBaseInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BpBaseInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCheckDate() != null, BpBaseInfo::getCheckDate, bo.getCheckDate());
        lqw.eq(bo.getDeptId() != null, BpBaseInfo::getDeptId, bo.getDeptId());
        return lqw;
    }

    /**
     * 新增档案基本信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(BpBaseInfoBo bo) {
        if (bo.getDeptId() == null) {
            throw new ServiceException("家庭不能为空！");
        }
        BpBaseInfo add = BeanUtil.toBean(bo, BpBaseInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改档案基本信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(BpBaseInfoBo bo) {
        BpBaseInfo update = BeanUtil.toBean(bo, BpBaseInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BpBaseInfo entity){
        //一个家庭同一日期只能存储一条数据
        LambdaQueryWrapper<BpBaseInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(BpBaseInfo::getCheckDate, entity.getCheckDate());
        lqw.eq(entity.getDeptId()!=null,BpBaseInfo::getDeptId, entity.getDeptId());
        lqw.eq(BpBaseInfo::getDelFlag,"0");
        List<BpBaseInfoVo> bpBaseInfoVos = this.baseMapper.selectVoList(lqw);
        if(bpBaseInfoVos!=null&& !bpBaseInfoVos.isEmpty()){
            throw new ServiceException("已存在该日期数据！");
        }
    }

    /**
     * 批量删除档案基本信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteBpBaseInfo(Long id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysOssVo uploadBpFile(Long baBaseId, String type, MultipartFile file) {
        log.debug("上传档案文件：" + file.getOriginalFilename());
        BpBaseInfoVo bpBaseInfoVo = this.queryById(baBaseId);
        if (bpBaseInfoVo == null) {
            throw new ServiceException("档案不存在,请刷新后重试！");
        }
        SysOssVo sysOssVo = iSysOssService.upload(file);
        BpBaseDetailInfoBo bo = new BpBaseDetailInfoBo();
        bo.setName(file.getOriginalFilename());
        bo.setType(type);
        bo.setBpBaseInfoId(baBaseId);
        bo.setFileId(sysOssVo.getOssId());
        if (iBpBaseDetailInfoService.insertByBo(bo)) {
            return sysOssVo;
        }
        return null;
    }
}
