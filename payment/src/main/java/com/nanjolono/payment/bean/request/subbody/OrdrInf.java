package com.nanjolono.payment.bean.request.subbody;


import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdrInf {

    @XmlElement(name = "OrdrId")
    private String OrdrId;

    @XmlElement(name = "OrdrDesc")
    private String OrdrDesc;

    @XmlElement(name = "OrdrDescRsv")
    private String OrdrDescRsv;

}
