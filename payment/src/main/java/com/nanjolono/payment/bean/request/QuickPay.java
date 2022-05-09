package com.nanjolono.payment.bean.request;

import com.nanjolono.payment.bean.request.subbody.BizAssInf;
import com.nanjolono.payment.bean.request.subbody.TrxInf;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class QuickPay {

    private Contract contract;

    public QuickPay() {
    }
}
