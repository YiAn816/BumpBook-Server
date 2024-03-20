package com.myda.common.translation.impl;

import com.myda.common.annotation.TranslationType;
import com.myda.common.constant.TransConstant;
import com.myda.common.core.service.OssService;
import com.myda.common.translation.TranslationInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * OSS翻译实现
 *
 * @author ccQi
 */
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.OSS_ID_TO_URL)
public class OssUrlTranslationImpl implements TranslationInterface<String> {

    private final OssService ossService;

    @Override
    public String translation(Object key, String other) {
        return ossService.selectUrlByIds(key.toString());
    }
}
