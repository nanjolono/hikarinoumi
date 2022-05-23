package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthSMSDTO {

    @ApiModelProperty(value = "交易金额")
    private String amount;

    @ApiModelProperty(value = "持卡人账号")
    private String rcverAcctId;

    @ApiModelProperty(value = "持卡人姓名")
    private String rcverNm;

    @ApiModelProperty(value = "证件类型")
    private String iDTp;

    @ApiModelProperty(value = "证件号码")
    private String iDNo;

    @ApiModelProperty(value = "预留手机号")
    private String mobNo;

}
