package com.myda.web.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.myda.common.config.MydaLifeConfig;
import com.myda.common.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author ccQi
 */
@RequiredArgsConstructor
@RestController
public class SysIndexController {

    /**
     * 系统基础配置
     */
    private final MydaLifeConfig mydaLifeConfig;

    /**
     * 访问首页，提示语
     */
    @SaIgnore
    @GetMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", mydaLifeConfig.getName(), mydaLifeConfig.getVersion());
    }
}
