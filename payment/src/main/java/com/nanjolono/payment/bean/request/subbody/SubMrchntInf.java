package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SubMrchntInf {

    //商户类别-特约商户所属的商户类别代码
    @XmlElement(name = "SubMrchntNo")
    private String SubMrchntNo;


}
