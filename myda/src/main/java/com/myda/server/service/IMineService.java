package com.myda.server.service;

import com.myda.common.core.domain.entity.SysDept;
import com.myda.common.core.domain.entity.SysUser;

import java.util.Date;
import java.util.List;

/**
 * @author ccQi
 * @Package com.myda.server.service
 * @Description: 用户信息管理服务
 * @date
 */
public interface IMineService {

    /**
     * 修改预产期
     *
     * @param deptId
     * @param ycDate
     */
    public void updateYcDateInfo(Long deptId, Date ycDate);

    /**
     * 根据部门id获取家庭成员列表
     *
     * @param deptId
     * @return
     */
    public List<SysUser> selectUserList();


    /**
     * 新增家庭信息
     * @param name
     * @param userId
     * @return
     */
    public SysUser addHomeInfo(String name, Long userId);

    /**
     * 校验家庭状态
     * @param userId
     * @return
     */
    public boolean checkHomeStatus(Long userId);
}
