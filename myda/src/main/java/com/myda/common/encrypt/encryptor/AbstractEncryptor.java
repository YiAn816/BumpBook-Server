package com.myda.common.encrypt.encryptor;

import com.myda.common.encrypt.EncryptContext;
import com.myda.common.encrypt.IEncryptor;

/**
 * 所有加密执行者的基类
 *
 * @author 老马
 * @version 4.6.0
 */
public abstract class AbstractEncryptor implements IEncryptor {

    public AbstractEncryptor(EncryptContext context) {
        // 用户配置校验与配置注入
    }

}
