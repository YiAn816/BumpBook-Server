package com.myda.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.myda.common.core.domain.entity.SysUser;
import com.myda.common.helper.LoginHelper;
import com.myda.server.domain.bo.BpBaseDetailInfoBo;
import com.myda.server.domain.vo.BpBaseDetailInfoVo;
import com.myda.server.service.IBpBaseDetailInfoService;
import com.myda.system.domain.vo.SysOssVo;
import com.myda.system.service.ISysDeptService;
import com.myda.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.myda.common.annotation.RepeatSubmit;
import com.myda.common.annotation.Log;
import com.myda.common.core.controller.BaseController;
import com.myda.common.core.domain.PageQuery;
import com.myda.common.core.domain.R;
import com.myda.common.core.validate.AddGroup;
import com.myda.common.core.validate.EditGroup;
import com.myda.common.enums.BusinessType;
import com.myda.common.utils.poi.ExcelUtil;
import com.myda.server.domain.vo.BpBaseInfoVo;
import com.myda.server.domain.bo.BpBaseInfoBo;
import com.myda.server.service.IBpBaseInfoService;
import com.myda.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 档案管理服务
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/myda/bp")
public class BpBaseInfoController extends BaseController {

    private final IBpBaseInfoService iBpBaseInfoService;

    private final IBpBaseDetailInfoService iBpBaseDetailInfoService;

    private final ISysUserService iSysUserService;

    /**
     * 分页查询档案基本信息列表
     */
    @GetMapping("/queryListByPage")
    public TableDataInfo<BpBaseInfoVo> queryListByPage(BpBaseInfoBo bo, PageQuery pageQuery) {
        return iBpBaseInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询档案基本信息列表
     */
    @GetMapping("/queryList")
    public R<List<BpBaseInfoVo>> queryList(BpBaseInfoBo bo) {
        SysUser sysUser = this.iSysUserService.selectUserById(getUserId());
        if (sysUser.getDeptId() == null) {
            return R.ok();
        }
        bo.setDeptId(sysUser.getDeptId());
        return R.ok(iBpBaseInfoService.queryList(bo));
    }

    /**
     * 新增档案基本信息
     */
    @Log(title = "档案基本信息", businessType = BusinessType.INSERT)
    @SaCheckRole("common")
    @RepeatSubmit()
    @PostMapping("/addBpBaseInfo")
    public R<Void> addBpBaseInfo(@Validated(AddGroup.class) @RequestBody BpBaseInfoBo bo) {
//        boolean b = StpUtil.hasRole("common");
        SysUser sysUser = this.iSysUserService.selectUserById(getUserId());
        bo.setDeptId(sysUser.getDeptId());
        return toAjax(iBpBaseInfoService.insertByBo(bo));
    }

    /**
     * 修改档案基本信息
     */
    @Log(title = "档案基本信息", businessType = BusinessType.UPDATE)
    @SaCheckRole("common")
    @RepeatSubmit()
    @PostMapping("/updateBpBaseInfo")
    public R<Void> updateBpBaseInfo(@Validated(EditGroup.class) @RequestBody BpBaseInfoBo bo) {
        SysUser sysUser = this.iSysUserService.selectUserById(getUserId());
        bo.setDeptId(sysUser.getDeptId());
        return toAjax(iBpBaseInfoService.updateByBo(bo));
    }

    /**
     * 删除档案基本信息
     *
     * @param id 主键
     */
    @Log(title = "删除档案基本信息", businessType = BusinessType.DELETE)
    @SaCheckRole("common")
    @PostMapping("/deleteBpBaseInfo/{id}")
    public R<Void> deleteBpBaseInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return toAjax(iBpBaseInfoService.deleteBpBaseInfo(id));
    }


    @SaCheckRole("common")
    @Log(title = "档案资料上传", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<Map<String, String>> upload(@RequestPart("file") MultipartFile file,
                                         @RequestParam("type") String type,
                                         @RequestParam("baBaseId") Long baBaseId) {
        log.debug("file={},type={},baBaseId={}", file, type, baBaseId);
        if (ObjectUtil.isNull(file)) {
            return R.fail("上传文件不能为空");
        }
        SysOssVo sysOssVo = iBpBaseInfoService.uploadBpFile(baBaseId, type, file);
        Map<String, String> map = new HashMap<>(4);
        map.put("url", sysOssVo.getUrl());
        map.put("fileName", sysOssVo.getOriginalName());
        map.put("ossId", sysOssVo.getOssId().toString());
        return R.ok(map);
    }

    /**
     * 删除档案详细信息
     *
     * @param id 主键
     */
    @Log(title = "删除档案详细信息", businessType = BusinessType.DELETE)
    @SaCheckRole("common")
    @PostMapping("/deleteBpBaseDetailInfo/{id}")
    public R<Void> deleteBpBaseDetailInfo(@NotNull(message = "主键不能为空")
                                          @PathVariable Long id) {
        return toAjax(iBpBaseDetailInfoService.deleteBpBaseDetailInfo(id));
    }
}
