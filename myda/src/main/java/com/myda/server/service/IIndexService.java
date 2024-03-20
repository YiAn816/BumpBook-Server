package com.myda.server.service;

import com.myda.common.core.domain.entity.SysUser;
import com.myda.server.domain.vo.PregnancyInfo;

import java.util.Date;
import java.util.List;

/**
 * @author ccQi
 * @Package com.myda.server.service
 * @Description: 首页信息服务
 * @date
 */
public interface IIndexService {


    /**
     * 根据家庭id获取周
     * @param deptId
     * @return
     */
    PregnancyInfo getPregnancyInfoByDeptId(Long deptId);



}
