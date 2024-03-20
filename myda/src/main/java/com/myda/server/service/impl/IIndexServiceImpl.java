package com.myda.server.service.impl;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.myda.common.core.domain.dto.RoleDTO;
import com.myda.common.core.domain.entity.SysDept;
import com.myda.common.core.domain.entity.SysUser;
import com.myda.common.core.domain.model.LoginUser;
import com.myda.common.exception.ServiceException;
import com.myda.server.domain.vo.PregnancyInfo;
import com.myda.server.service.IIndexService;
import com.myda.server.service.IMineService;
import com.myda.server.utils.MydaDateUtils;
import com.myda.system.service.ISysDeptService;
import com.myda.system.service.ISysRoleService;
import com.myda.system.service.ISysUserService;
import com.myda.system.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.myda.common.helper.LoginHelper.LOGIN_USER_KEY;
import static com.myda.common.helper.LoginHelper.getUserId;

/**
 * @author ccQi
 * @Package com.myda.server.service
 * @Description: 用户信息管理服务
 * @date
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class IIndexServiceImpl implements IIndexService {

    private final ISysDeptService iSysDeptService;
    @Override
    public PregnancyInfo getPregnancyInfoByDeptId(Long deptId) {
        PregnancyInfo pregnancyInfo = new PregnancyInfo();
        SysDept sysDept = this.iSysDeptService.selectDeptById(deptId);
        if (sysDept == null) {
            throw new ServiceException("家庭信息不存在！");
        }
        if (sysDept.getDueDate() == null) {
            sysDept.setDueDate(new Date());
        }
        pregnancyInfo.setDueDate(sysDept.getDueDate());
        int weeks = MydaDateUtils.calculatePregnancyWeeks(sysDept.getDueDate());
        pregnancyInfo.setWeeks(weeks);
        if (weeks >= 1 && weeks < 12) {
            pregnancyInfo.setPregnancyType(0);
        } else if (weeks >= 12 && weeks < 27) {
            pregnancyInfo.setPregnancyType(1);
        } else if (weeks >= 27 && weeks <= 40) {
            pregnancyInfo.setPregnancyType(2);
        }
        long days = MydaDateUtils.daysBetween(new Date(), sysDept.getDueDate());
        pregnancyInfo.setDays(Integer.parseInt(String.valueOf(days)));
        return pregnancyInfo;
    }
}
