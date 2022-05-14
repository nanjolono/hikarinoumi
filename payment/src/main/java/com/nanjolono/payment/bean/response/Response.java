package com.nanjolono.payment.bean.response;


import com.nanjolono.payment.bean.request.subbody.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    @XmlElement(name = "BizTp")
    private String BizTp;

    @XmlElement(name = "BizFunc")
    private String BizFunc;

    @XmlElement(name = "BizAssInf")
    private BizAssInf bizAssInf;

    @XmlElement(name = "TrxInf")
    private TrxInf trxInf;

    @XmlElement(name = "RcverInf")
    private RcverInf  rcverInf;

    @XmlElement(name = "SderInf")
    private SderInf sderInf;

    @XmlElement(name = "SysRtnInf")
    private SysRtnInf sysRtnInf;

}

