package com.nanjolono.payment.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

@ApiModel
public class SignDto {

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


    public void checkParam() {
        isNotEmpty(this.mobNo,"电话号码");
        isNotEmpty(this.idNo,"身份证号码");
        isNotEmpty(this.idTp,"证件类型");
        isNotEmpty(this.rcverAcct,"持卡人账号");
        isNotEmpty(this.rcverNm,"持卡人姓名");
    }

    private void isNotEmpty(String rcverNm,String paramName) {
        if (StringUtils.isEmpty(rcverNm)) {
            throw new IllegalArgumentException(paramName + ":不能为空");
        }
    }

    @Override
    public String toString() {
        return "SignDto{" +
                "mobNo='" + mobNo + '\'' +
                ", idNo='" + idNo + '\'' +
                ", idTp='" + idTp + '\'' +
                ", rcverAcct='" + rcverAcct + '\'' +
                ", rcverNm='" + rcverNm + '\'' +
                '}';
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdTp() {
        return idTp;
    }

    public void setIdTp(String idTp) {
        this.idTp = idTp;
    }

    public String getRcverAcct() {
        return rcverAcct;
    }

    public void setRcverAcct(String rcverAcct) {
        this.rcverAcct = rcverAcct;
    }

    public String getRcverNm() {
        return rcverNm;
    }

    public void setRcverNm(String rcverNm) {
        this.rcverNm = rcverNm;
    }
}
