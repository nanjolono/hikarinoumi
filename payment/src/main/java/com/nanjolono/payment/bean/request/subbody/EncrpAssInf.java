package com.nanjolono.payment.bean.request.subbody;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class EncrpAssInf {

    //加密信息签名证<EncrpSignSN>书序列号
    @XmlElement(name = "EncrpSignSN")
    private String EncrpSignSN;

    //加密信息加密证书序列号
    @XmlElement(name = "EncrpEncSN")
    private String EncrpEncSN;

    //加密信息对称加密密钥
    @XmlElement(name = "EncrpEncKey")
    private String EncrpEncKey;

    //加密信息签名和密钥加密算法类型
    @XmlElement(name = "EncrpSignEncAlgo")
    private String EncrpSignEncAlgo;

    //加密信息摘要算法类型
    @XmlElement(name = "EncrpMDAlgo")
    private String EncrpMDAlgo;

    //加密信息对称加密算法类型
    @XmlElement(name = "EncrpEncAlgo")
    private String EncrpEncAlgo;

    //加密信息
    @XmlElement(name = "EncrpMssg")
    private String EncrpMssg;
}
