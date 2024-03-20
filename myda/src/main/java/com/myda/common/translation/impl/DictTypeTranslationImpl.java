package com.myda.common.translation.impl;

import com.myda.common.annotation.TranslationType;
import com.myda.common.constant.TransConstant;
import com.myda.common.core.service.DictService;
import com.myda.common.translation.TranslationInterface;
import com.myda.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 字典翻译实现
 *
 * @author ccQi
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, key.toString());
        }
        return null;
    }
}
