package com.nanjolono.payment.response;

import lombok.Data;

/**
 * @Author 张昊坤
 * @Description 二维码交易服务调用返回
 * @Date 2021/3/17 14:08
 */
@Data
public class Response {
    /**
     * 返回码
     */
    private String respCode;

    /**
     * 返回详情
     */
    private String respMsg;

    /**
     * 返回实体
     */
    private Object transInfo;

    /**
     * MAC校验值
     */
    private String macValue;

    public Response(String code, String desc, Object transInfo) {
        this.respCode = code;
        this.respMsg = desc;
        this.transInfo = transInfo;

    }

    public Response(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public Response(String code, String desc, Object transInfo, String macValue) {
        this.respCode = code;
        this.respMsg = desc;
        this.transInfo = transInfo;
        this.macValue = macValue;
    }

    public Response() {
    }

    @Override
    public String toString() {
        return "Response{" +
                "respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", transInfo=" + transInfo +
                ", macValue='" + macValue + '\'' +
                '}';
    }
}