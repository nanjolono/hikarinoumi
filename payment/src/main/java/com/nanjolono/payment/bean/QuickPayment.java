package com.nanjolono.payment.bean;


import com.nanjolono.payment.bean.request.Contract;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
/**
 * 快捷支付入口
 * @version 1.0
 * @author nanjolono
 */
@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@XmlSeeAlso(value = {Contract.class})
public class QuickPayment implements Serializable {

    /**
     *
     */
    @XmlElement(name="MsgHeader")
    private MsgHeader msgHeader;

    @XmlElement(name="MsgBody")
    private MsgBody msgBody;

    public QuickPayment() {
    }

    public QuickPayment(MsgHeader msgHeader, MsgBody msgBody) {
        this.msgHeader = msgHeader;
        this.msgBody = msgBody;
    }

    public static final class QuickPaymentBuilder {
        private QuickPayment quickPayment;

        private QuickPaymentBuilder(MsgHeader msgHeader, MsgBody msgBody) {
            quickPayment = new QuickPayment(msgHeader,msgBody);
        }

        private QuickPaymentBuilder() {
            quickPayment = new QuickPayment();
        }

        public static QuickPaymentBuilder aQuickPayment(MsgHeader msgHeader, MsgBody msgBody) {
            return new QuickPaymentBuilder(msgHeader,msgBody);
        }

        public static QuickPaymentBuilder aQuickPayment() {
            return new QuickPaymentBuilder();
        }

        public QuickPaymentBuilder msgHeader(MsgHeader msgHeader) {
            quickPayment.setMsgHeader(msgHeader);
            return this;
        }

        public QuickPaymentBuilder msgBody(MsgBody msgBody) {
            quickPayment.setMsgBody(msgBody);
            return this;
        }

        public QuickPayment build() {
            return quickPayment;
        }
    }
}

