package com.myda.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myda.common.annotation.Translation;
import com.myda.common.core.domain.BaseEntity;
import com.myda.common.core.domain.PageQuery;
import com.myda.common.core.domain.entity.SysDept;
import com.myda.common.core.domain.entity.SysUser;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.exception.ServiceException;
import com.myda.server.domain.BpDeptApplyInfo;
import com.myda.server.domain.bo.BpDeptApplyInfoBo;
import com.myda.server.domain.vo.BpDeptApplyInfoVo;
import com.myda.server.mapper.BpDeptApplyMapper;
import com.myda.server.service.IBpDeptApplyService;
import com.myda.system.service.ISysDeptService;
import com.myda.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 申请信息表Service业务层处理
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BpDeptApplyServiceImpl implements IBpDeptApplyService {

    private final BpDeptApplyMapper baseMapper;

    private final ISysUserService iSysUserService;

    private final ISysDeptService iSysDeptService;

    /**
     * 查询申请信息表
     */
    @Override
    public BpDeptApplyInfoVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询申请信息表列表
     */
    @Override
    public TableDataInfo<BpDeptApplyInfoVo> queryPageList(BpDeptApplyInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BpDeptApplyInfo> lqw = buildQueryWrapper(bo);
        Page<BpDeptApplyInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询申请信息表列表
     */
    @Override
    public List<BpDeptApplyInfoVo> queryList(BpDeptApplyInfoBo bo) {
        LambdaQueryWrapper<BpDeptApplyInfo> lqw = buildQueryWrapper(bo);
        List<BpDeptApplyInfoVo> BpDeptApplyInfoVos = baseMapper.selectVoList(lqw);
        for (BpDeptApplyInfoVo bpDeptApplyInfoVo : BpDeptApplyInfoVos) {
            SysUser sysUser = iSysUserService.selectUserById(bpDeptApplyInfoVo.getUserId());
            if (sysUser != null) {
                bpDeptApplyInfoVo.setName(sysUser.getNickName());
            }else{
                bpDeptApplyInfoVo.setName("该用户已删除");
                bpDeptApplyInfoVo.setApplyStatus(2);
            }
        }
        return BpDeptApplyInfoVos;
    }

    private LambdaQueryWrapper<BpDeptApplyInfo> buildQueryWrapper(BpDeptApplyInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BpDeptApplyInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getDeptId() != null, BpDeptApplyInfo::getDeptId, bo.getDeptId());
        lqw.eq(bo.getUserId() != null, BpDeptApplyInfo::getUserId, bo.getUserId());
        lqw.eq(bo.getApplyStatus() != null, BpDeptApplyInfo::getApplyStatus, bo.getApplyStatus());
        lqw.orderByAsc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增申请信息表
     */
    @Override
    public Boolean insertByBo(BpDeptApplyInfoBo bo) {
        BpDeptApplyInfo add = BeanUtil.toBean(bo, BpDeptApplyInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改申请信息表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(BpDeptApplyInfoBo bo) {
        BpDeptApplyInfo update = BeanUtil.toBean(bo, BpDeptApplyInfo.class);
        validEntityBeforeSave(update);
        if (bo.getApplyStatus() == 1) {
            //修改对应家庭信息
            BpDeptApplyInfo bpDeptApplyInfo = baseMapper.selectById(bo.getId());
            SysUser sysUser = iSysUserService.selectUserById(bpDeptApplyInfo.getUserId());
            sysUser.setDeptId(bpDeptApplyInfo.getDeptId());
            iSysUserService.updateUserProfile(sysUser);
        }
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(BpDeptApplyInfo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除申请信息表
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean applyJoinDept(Long deptId, Long userId, String remark) {
        SysDept sysDept = this.iSysDeptService.selectDeptById(deptId);
        if (sysDept == null) {
            throw new ServiceException("请输入正确的家庭号码！");
        }
        BpDeptApplyInfoBo bo = new BpDeptApplyInfoBo();
        bo.setDeptId(deptId);
        bo.setUserId(userId);
        bo.setApplyTime(new Date());
        bo.setApplyStatus(0);
        bo.setRemark(remark);
        return this.insertByBo(bo);
    }
}
