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


    public static final class ChannelIssInfBuilder {
        private ChannelIssInf channelIssInf;

        private ChannelIssInfBuilder() {
            channelIssInf = new ChannelIssInf();
        }

        public static ChannelIssInfBuilder aChannelIssInf() {
            return new ChannelIssInfBuilder();
        }

        public ChannelIssInfBuilder ChannelIssrId(String ChannelIssrId) {
            channelIssInf.setChannelIssrId(ChannelIssrId);
            return this;
        }

        public ChannelIssInfBuilder SgnNo(String SgnNo) {
            channelIssInf.setSgnNo(SgnNo);
            return this;
        }

        public ChannelIssInf build() {
            return channelIssInf;
        }
    }
}
