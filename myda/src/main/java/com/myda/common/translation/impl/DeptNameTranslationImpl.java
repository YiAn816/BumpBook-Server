package com.myda.common.translation.impl;

import com.myda.common.annotation.TranslationType;
import com.myda.common.constant.TransConstant;
import com.myda.common.core.service.DeptService;
import com.myda.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门翻译实现
 *
 * @author ccQi
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    private final DeptService deptService;

    @Override
    public String translation(Object key, String other) {
        return deptService.selectDeptNameByIds(key.toString());
    }
}
