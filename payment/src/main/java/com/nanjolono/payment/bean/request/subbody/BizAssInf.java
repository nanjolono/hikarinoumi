package com.nanjolono.payment.bean.request.subbody;


import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BizAssInf {

    //分期期数 MaxMin2Text 业务功能为分期消费时该字段必填，仅限数字
    @XmlElement(name = "IPNum")
    private String IPNum;

    //商户补贴分交期手续费方式  MaxMin1Text  0-不贴息，1-部分贴息，2-全额 贴息业务功能为分期消费时该字段必填
    @XmlElement(name = "IPMode")
    private String IPMode;

    //商户分期贴 MaxMin6Text 息费率表示商户最高补贴手续费率，仅 限数字，取值为商户补贴手续费 率*100000，例如商户贴息费率为 4.5%，则该字段取值为 004500 若“商户补贴分期手续费方式” 取值为 0-不贴息时，该字段取值 为 000000
    @XmlElement(name = "IPMrchntExpRate")
    private String IPMrchntExpRate;

    //业务辅助信息预留
    @XmlElement(name = "BizAssInfRsv")
    private String BizAssInfRsv;

    //原交易授权码 MaxMin6Text
    @XmlElement(name = "PreAuthId")
    private String PreAuthId;

    //原交易流水号 MaxMin16Text
    @XmlElement(name = "OriTrxId")
    private String OriTrxId;


    public static final class BizAssInfBuilder {
        private BizAssInf bizAssInf;

        private BizAssInfBuilder() {
            bizAssInf = new BizAssInf();
        }

        public static BizAssInfBuilder aBizAssInf() {
            return new BizAssInfBuilder();
        }

        public BizAssInfBuilder IPNum(String IPNum) {
            bizAssInf.setIPNum(IPNum);
            return this;
        }

        public BizAssInfBuilder IPMode(String IPMode) {
            bizAssInf.setIPMode(IPMode);
            return this;
        }

        public BizAssInfBuilder IPMrchntExpRate(String IPMrchntExpRate) {
            bizAssInf.setIPMrchntExpRate(IPMrchntExpRate);
            return this;
        }

        public BizAssInfBuilder BizAssInfRsv(String BizAssInfRsv) {
            bizAssInf.setBizAssInfRsv(BizAssInfRsv);
            return this;
        }

        public BizAssInfBuilder PreAuthId(String PreAuthId) {
            bizAssInf.setPreAuthId(PreAuthId);
            return this;
        }

        public BizAssInfBuilder OriTrxId(String OriTrxId) {
            bizAssInf.setOriTrxId(OriTrxId);
            return this;
        }

        public BizAssInf build() {
            return bizAssInf;
        }
    }
}
