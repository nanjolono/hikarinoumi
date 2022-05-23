package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthTransDTO {

    @ApiModelProperty(value = "交易金额")
    private String amount;

    @ApiModelProperty(value = "交易终端类型")
    private String trxTrmTp;

    @ApiModelProperty(value = "付款方账号")
    private String pyerAcctId;

    @ApiModelProperty(value = "消费类型")
    private String quickType;

    @ApiModelProperty(value = "分期笔数")
    private String num;

    @ApiModelProperty(value = "短信验证码")
    private String authMsg;
}
