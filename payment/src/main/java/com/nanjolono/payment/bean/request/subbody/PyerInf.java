package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PyerInf {

    //付款方账户 Max34Text 关联交易为快捷类交易时必送
    @XmlElement(name = "PyerAcctId")
    private String PyerAcctId;

    //付款方名称 Max60Text PyerNm
    @XmlElement(name = "PyerNm")
    private String PyerNm;

    //付款方证件类型 IDTpCd 证件号和证件类型应同时出现或不出现
    @XmlElement(name = "IDTp")
    private String IDTp;

    //付款方证件号  Max20Text 证件号和证件类型应同时出现或不出现
    @XmlElement(name = "IDNo")
    private String IDNo;

    //付款方预留手 Max18Text
    @XmlElement(name = "MobNo")
    private String MobNo;

    @XmlElement(name = "PyerAcctIssrId")
    private String PyerAcctIssrId;

    @XmlElement(name = "PyeeIssrId")
    private String PyeeIssrId;
}
