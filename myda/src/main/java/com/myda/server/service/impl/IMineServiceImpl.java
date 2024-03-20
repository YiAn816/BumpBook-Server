package com.myda.server.service.impl;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.myda.common.core.domain.dto.RoleDTO;
import com.myda.common.core.domain.entity.SysDept;
import com.myda.common.core.domain.entity.SysUser;
import com.myda.common.core.domain.model.LoginUser;
import com.myda.common.exception.ServiceException;
import com.myda.server.service.IMineService;
import com.myda.server.utils.MydaUtils;
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

import static com.myda.common.helper.LoginHelper.*;

/**
 * @author ccQi
 * @Package com.myda.server.service
 * @Description: 用户信息管理服务
 * @date
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class IMineServiceImpl implements IMineService {

    private final ISysUserService iSysUserService;

    private final ISysDeptService iSysDeptService;

    private final ISysRoleService iSysRoleService;

    private final SysPermissionService permissionService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateYcDateInfo(Long deptId, Date ycDate) {
        SysDept sysDept = new SysDept();
        sysDept.setDeptId(deptId);
        sysDept.setDueDate(ycDate);
        iSysDeptService.updateDeptProfile(sysDept);
    }

    @Override
    public List<SysUser> selectUserList() {
        if (getDeptId() == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setDeptId( getDeptId());
        return iSysUserService.selectUserList(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser addHomeInfo(String name, Long userId) {
        log.info("新增家庭信息{}", name);
        SysUser sysUser = iSysUserService.selectUserById(userId);
        if (sysUser.getDeptId() != null) {
            throw new ServiceException("已存在家庭信息，不允许创建！");
        }
        SysDept sysDept = new SysDept();
        sysDept.setDeptName(name);
        sysDept.setStatus("0");
        sysDept.setParentId(100L);
        sysDept.setDeptId(Long.parseLong(MydaUtils.getId()));
        iSysDeptService.insertDept(sysDept);
        //授权
        Long[] roleIds = new Long[]{2L};
        sysUser.setRoleIds(roleIds);
        sysUser.setDeptId(sysDept.getDeptId());
        iSysUserService.updateUser(sysUser);
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        loginUser.setDeptId(sysDept.getDeptId());
        loginUser.setRolePermission(permissionService.getRolePermission(sysUser));
        List<RoleDTO> roles = BeanUtil.copyToList(sysUser.getRoles(), RoleDTO.class);
        loginUser.setRoles(roles);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
        return sysUser;
    }

    @Override
    public boolean checkHomeStatus(Long userId) {
        SysUser sysUser = this.iSysUserService.selectUserById(userId);
        return sysUser != null && sysUser.getDeptId() != null;
    }
}
