package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

@Data
@ToString
public class ContractDto {

    @ApiModelProperty(value = "手机号")
    private String mobNo;

    @ApiModelProperty(value = "身份证号")
    private String idNo;

    @ApiModelProperty(value = "证件类型 01-身份证 02-军官证 " +
            "03-护照 08-户口簿 07-士兵证 04-港澳来往内地通行证 05-台湾同胞来往内地通行证" +
            "09-临时身份证 10-外国人居留证 06-警官证 12-港澳居民居住证" +
            "13-台湾居民居住证")
    private String idTp;

    @ApiModelProperty(value = "持卡人账号")
    private String rcverAcct;

    @ApiModelProperty(value = "持卡人名称")
    private String rcverNm;

    @ApiModelProperty(value = "验证码")
    private String authMsg;

    public void checkParam() {
        isNotEmpty(this.mobNo,"手机号码");
        isNotEmpty(this.idNo,"身份证号码");
        isNotEmpty(this.idTp,"证件类型");
        isNotEmpty(this.rcverAcct,"持卡人卡号");
        isNotEmpty(this.rcverNm,"持卡人姓名");
        isNotEmpty(this.authMsg,"验证短信信息");
    }

    private void isNotEmpty(String rcverNm,String paramNm) {
        if (StringUtils.isEmpty(rcverNm)) {
            throw new IllegalArgumentException(paramNm + "参数不能为空");
        }
    }
}
