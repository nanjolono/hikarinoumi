package com.nanjolono.payment.bean.request.subbody;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SysRtnInf {

    @XmlElement(name = "SysRtnCd")
    private String SysRtnCd;

    @XmlElement(name = "SysRtnDesc")
    private String SysRtnDesc;

    @XmlElement(name = "SysRtnTm")
    private String SysRtnTm;

    @XmlElement(name = "ValBtMp")
    private String ValBtMp;


}
