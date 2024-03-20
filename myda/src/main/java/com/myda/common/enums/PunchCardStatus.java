package com.myda.common.enums;

/**
 * 打卡状态
 *
 * @author daily
 */
//打卡状态(0-待定，1-完成，2-失败)
public enum PunchCardStatus {
    OK("0", "待定"), DISABLE("1", "完成"), DELETED("2", "失败");

    private final String code;
    private final String info;

    PunchCardStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
