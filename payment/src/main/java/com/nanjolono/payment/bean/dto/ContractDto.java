package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

@Data
@ToString
public class ContractDto {

    @ApiModelProperty(name = "手机号")
    private String mobNo;

    @ApiModelProperty(name = "身份证号")
    private String idNo;

    @ApiModelProperty(name = "证件类型 01-身份证 02-军官证 " +
            "03-护照 08-户口簿 07-士兵证 04-港澳来往内地通行证 05-台湾同胞来往内地通行证" +
            "09-临时身份证 10-外国人居留证 06-警官证 12-港澳居民居住证" +
            "13-台湾居民居住证")
    private String idTp;

    @ApiModelProperty(name = "持卡人账号")
    private String rcverAcct;

    @ApiModelProperty(name = "持卡人名称")
    private String rcverNm;

    @ApiModelProperty(name = "验证码")
    private String authMsg;

    @ApiModelProperty(name = "验证码密钥")
    private String smskey;

    public void checkParam() {
        isNotEmpty(this.mobNo);
        isNotEmpty(this.idNo);
        isNotEmpty(this.idTp);
        isNotEmpty(this.rcverAcct);
        isNotEmpty(this.rcverNm);
        isNotEmpty(this.authMsg);
        isNotEmpty(this.smskey);
    }

    private void isNotEmpty(String rcverNm) {
        if (StringUtils.isEmpty(rcverNm)) {
            throw new IllegalArgumentException("参数不能为空");
        }
    }
}
