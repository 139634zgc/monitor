package com.bitauto.bdc.modules.hdfs.constant;

/**
 * Created by weiyongxu on 2017/10/24.
 */
public enum ErrorCode {
    ERR_HDFS_JMX_API(-1000, "HDFS JMX API ERROR");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
