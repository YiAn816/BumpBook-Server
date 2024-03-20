package com.myda.server.service;

import com.myda.server.domain.BpBaseInfo;
import com.myda.server.domain.vo.BpBaseInfoVo;
import com.myda.server.domain.bo.BpBaseInfoBo;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.core.domain.PageQuery;
import com.myda.system.domain.vo.SysOssVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * 档案基本信息Service接口
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
public interface IBpBaseInfoService {

    /**
     * 查询档案基本信息
     */
    BpBaseInfoVo queryById(Long id);

    /**
     * 查询档案基本信息列表
     */
    TableDataInfo<BpBaseInfoVo> queryPageList(BpBaseInfoBo bo, PageQuery pageQuery);

    /**
     * 查询档案基本信息列表
     */
    List<BpBaseInfoVo> queryList(BpBaseInfoBo bo);

    /**
     * 新增档案基本信息
     */
    Boolean insertByBo(BpBaseInfoBo bo);

    /**
     * 修改档案基本信息
     */
    Boolean updateByBo(BpBaseInfoBo bo);

    /**
     * 校验并批量删除档案基本信息信息
     */
    Boolean deleteBpBaseInfo(Long id);

    /**
     * 档案文件上传
     * @param baBaseId
     * @param type
     * @param file
     * @return
     */
    SysOssVo uploadBpFile(Long baBaseId, String type, MultipartFile file);
}
