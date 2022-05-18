package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickPayDTO {

    //终端类型
    @ApiModelProperty(value = "终端类型")
    private String trxTp;

    @ApiModelProperty(value = "支付金额 - 精确到小数点后两位")
    private String amount;

    @ApiModelProperty(value = "交易类型 1-全额支付 2-分期 3-预授权")
    private String qucikType;

    @ApiModelProperty(value = "分期期数")
    private String num;

    @ApiModelProperty(value = "支付者账号")
    private String pyerAcctId;

    public void checkParam() {
        isNotEmpty(this.trxTp);
        isNotEmpty(this.amount);
        isNotEmpty(this.qucikType);
        isNotEmpty(this.num);
        isNotEmpty(this.pyerAcctId);
    }

    private void isNotEmpty(String rcverNm) {
        if (StringUtils.isEmpty(rcverNm)) {
            throw new IllegalArgumentException("参数不能为空");
        }
    }
}
