package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class AuthInfo {

    //业务种类
    @XmlElement(name = "BizTp")
    private String bizTp;

    //业务功能
    @XmlElement(name = "BizFunc")
    private String BizFunc;

    //业务辅助信息
    @XmlElement(name = "BizAssInf")
    private BizAssInf bizAssInf;

    //交易信息
    @XmlElement(name = "TrxInf")
    private TrxInf trxInf;

    //接收方信息
    @XmlElement(name = "RcverInf")
    private RcverInf rcverInf;

    //发起方信息
    @XmlElement(name = "SderInf")
    private SderInf sderInf;



}
