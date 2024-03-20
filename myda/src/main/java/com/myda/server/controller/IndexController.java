package com.myda.server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.myda.common.annotation.Log;
import com.myda.common.annotation.RepeatSubmit;
import com.myda.common.core.controller.BaseController;
import com.myda.common.core.domain.R;
import com.myda.common.core.domain.entity.SysUser;
import com.myda.common.core.validate.EditGroup;
import com.myda.common.enums.BusinessType;
import com.myda.common.utils.DateUtils;
import com.myda.server.domain.vo.BpPregnancyWeekVo;
import com.myda.server.domain.vo.PregnancyInfo;
import com.myda.server.service.IBpPregnancyWeekService;
import com.myda.server.service.IIndexService;
import com.myda.server.service.IMineService;
import com.myda.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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
@RequestMapping("/myda/index")
public class IndexController extends BaseController {

    private final IIndexService iIndexService;

    private final IBpPregnancyWeekService iBpPregnancyWeekService;

    /**
     * 修改预产期
     *
     * @return
     */
    @GetMapping("/getPregnancyInfo")
    public R<PregnancyInfo> getPregnancyInfo() {
        PregnancyInfo pregnancyInfo = iIndexService.getPregnancyInfoByDeptId(getDeptId());
        return R.ok(pregnancyInfo);
    }

    /**
     * 获取预产期信息
     *
     * @param week
     * @return
     */
    @GetMapping("/getPregnancyWeekInfo/{week}")
    public R<BpPregnancyWeekVo> getPregnancyWeekInfo(@NotNull(message = "周期不能为空") @PathVariable("week") Integer week) {
        return R.ok(iBpPregnancyWeekService.queryByWeek(week));
    }
}
