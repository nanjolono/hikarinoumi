package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CorpCard {

    //单位结算卡完整账户名称
    @XmlElement(name = "CorpName")
    private String CorpName;

    //营业执照注册号
    @XmlElement(name = "USCCode")
    private String USCCode;

}
