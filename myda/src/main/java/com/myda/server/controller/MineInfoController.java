package com.myda.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.util.ObjectUtil;
import com.myda.common.annotation.Log;
import com.myda.common.annotation.RepeatSubmit;
import com.myda.common.core.controller.BaseController;
import com.myda.common.core.domain.PageQuery;
import com.myda.common.core.domain.R;
import com.myda.common.core.domain.entity.SysDept;
import com.myda.common.core.domain.entity.SysRole;
import com.myda.common.core.domain.entity.SysUser;
import com.myda.common.core.page.TableDataInfo;
import com.myda.common.core.validate.AddGroup;
import com.myda.common.core.validate.EditGroup;
import com.myda.common.enums.BusinessType;
import com.myda.common.helper.LoginHelper;
import com.myda.common.utils.DateUtils;
import com.myda.common.utils.StreamUtils;
import com.myda.server.domain.bo.BpBaseInfoBo;
import com.myda.server.domain.bo.BpDeptApplyInfoBo;
import com.myda.server.domain.vo.BpBaseInfoVo;
import com.myda.server.domain.vo.BpDeptApplyInfoVo;
import com.myda.server.service.IBpBaseDetailInfoService;
import com.myda.server.service.IBpBaseInfoService;
import com.myda.server.service.IBpDeptApplyService;
import com.myda.server.service.IMineService;
import com.myda.system.domain.vo.SysOssVo;
import com.myda.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基本信息管理服务
 *
 * @author sweet-org99
 * @date 2024-03-06
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/myda/mine")
public class MineInfoController extends BaseController {

    private final IMineService iMineService;

    private final ISysUserService iSysUserService;

    private final IBpDeptApplyService iBpDeptApplyService;

    /**
     * 修改预产期
     *
     * @param ycDate
     * @return
     */
    @Log(title = "修改预产期", businessType = BusinessType.UPDATE)
    @SaCheckRole("common")
    @RepeatSubmit()
    @PostMapping("/updateYcDateInfo/{ycDate}")
    public R<Void> updateYcDateInfo(@Validated(EditGroup.class) @PathVariable String ycDate) {
        Date date = DateUtils.parseDate(ycDate);
        iMineService.updateYcDateInfo(getDeptId(), date);
        return R.ok();
    }

    /**
     * 校验当前用户是否已经加入家庭
     *
     * @return
     */
    @GetMapping("/checkHomeStatus")
    public R<Boolean> checkHomeStatus() {
        return R.ok(iMineService.checkHomeStatus(getUserId()));
    }

    /**
     * 获取用户列表
     */
    @GetMapping("/getUserList")
    public R<List<SysUser>> getUserList() {
        List<SysUser> sysUsers = iMineService.selectUserList();
        return R.ok(sysUsers);
    }

    /**
     * 新增家庭信息
     *
     * @param homeName
     * @return
     */
    @Log(title = "新增家庭信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/addHomeInfo/{homeName}")
    public R<SysUser> addHomeInfo(@Validated(EditGroup.class) @PathVariable String homeName) {
        SysUser sysUser = iMineService.addHomeInfo(homeName, getUserId());
        return R.ok(sysUser);
    }

    /**
     * 申请加入家庭
     *
     * @param applyInfo
     * @return
     */
    @Log(title = "加入家庭", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/applyJoinDept")
    public R<Boolean> applyJoinDept(@Validated(AddGroup.class) BpDeptApplyInfoBo bo) {
        Boolean b = this.iBpDeptApplyService.applyJoinDept(bo.getDeptId(), getUserId(), bo.getRemark());
        return R.ok(b);
    }


    /**
     * 查询家庭申请信息列表
     *
     * @return
     */
    @GetMapping("/queryDeptApplyInfoList")
    @SaCheckRole("common")
    public R<List<BpDeptApplyInfoVo>> queryDeptApplyInfoList() {
        BpDeptApplyInfoBo bo = new BpDeptApplyInfoBo();
        bo.setDeptId(getDeptId());
        List<BpDeptApplyInfoVo> bpDeptApplyInfoVos = iBpDeptApplyService.queryList(bo);
        return R.ok(bpDeptApplyInfoVos);
    }

    /**
     * 修改申请状态
     *
     * @param bo
     * @return
     */
    @PostMapping("/updateApplyStatus")
    @SaCheckRole("common")
    public R<Boolean> updateApplyStatus(BpDeptApplyInfoBo bo) {
        Boolean b = iBpDeptApplyService.updateByBo(bo);
        return R.ok(b);
    }

//    /**
//     * 解散家庭
//     *
//     * @param bo
//     * @return
//     */
//    @PostMapping("/updateApplyStatus")
//    @SaCheckRole("common")
//    public R<Boolean> updateApplyStatus() {
//        get
//        Boolean b = iSysUserService.updateUserProfile(bo);
//        return R.ok(b);
//    }
}
