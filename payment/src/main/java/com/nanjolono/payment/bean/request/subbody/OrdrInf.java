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


    public static final class OrdrInfBuilder {
        private OrdrInf ordrInf;

        private OrdrInfBuilder() {
            ordrInf = new OrdrInf();
        }

        public static OrdrInfBuilder anOrdrInf() {
            return new OrdrInfBuilder();
        }

        public OrdrInfBuilder OrdrId(String OrdrId) {
            ordrInf.setOrdrId(OrdrId);
            return this;
        }

        public OrdrInfBuilder OrdrDesc(String OrdrDesc) {
            ordrInf.setOrdrDesc(OrdrDesc);
            return this;
        }

        public OrdrInfBuilder OrdrDescRsv(String OrdrDescRsv) {
            ordrInf.setOrdrDescRsv(OrdrDescRsv);
            return this;
        }

        public OrdrInf build() {
            return ordrInf;
        }
    }
}
