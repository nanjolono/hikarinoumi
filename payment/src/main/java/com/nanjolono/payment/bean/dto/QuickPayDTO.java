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
    @ApiModelProperty(value = "终端类型 07-电脑 08-手机 10-平板设备 18-可穿戴设备 19-数字电视 00-其他")
    private String trxTp;

    @ApiModelProperty(value = "支付金额 - 精确到小数点后两位")
    private String amount;

    @ApiModelProperty(value = "交易类型 1-全额支付 2-分期 3-预授权")
    private String qucikType;

    @ApiModelProperty(value = "分期期数 交易类型为2时填写")
    private String num;

    @ApiModelProperty(value = "支付者账号")
    private String pyerAcctId;

    @ApiModelProperty(value = "商户号")
    private String mchntCd;

    public void checkParam() {
        isNotEmpty(this.trxTp,"终端类型");
        isNotEmpty(this.amount,"交易金额");
        isNotEmpty(this.qucikType,"交易类型");
        if ("2".equals(this.getQucikType())) {
            isNotEmpty(this.num,"分期笔数");
        }
        isNotEmpty(this.pyerAcctId,"持卡人账号");
    }

    private void isNotEmpty(String rcverNm,String paramNm) {
        if (StringUtils.isEmpty(rcverNm)) {
            throw new IllegalArgumentException(paramNm + "参数不能为空");
        }
    }
}
