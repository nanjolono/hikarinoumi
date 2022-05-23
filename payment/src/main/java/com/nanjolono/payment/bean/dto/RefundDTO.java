package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RefundDTO {

    @ApiModelProperty(value = "原订单Id")
    private String oriTrxId;

    @ApiModelProperty(value = "退款金额")
    private String amount;

    @ApiModelProperty(value = "交易终端类型")
    private String trxTrmTp;

    @ApiModelProperty(value = "退款类型 4-全额退款 5-预授权解冻 6-预授权完成")
    private String refundType;

}
