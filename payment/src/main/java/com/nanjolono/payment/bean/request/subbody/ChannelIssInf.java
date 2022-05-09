package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelIssInf {

    @XmlElement(name = "ChannelIssrId")
    private String ChannelIssrId;

    @XmlElement(name = "SgnNo")
    private String SgnNo;
}
