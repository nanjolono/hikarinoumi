package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SderInf {

    //发送机构标识
    @XmlElement(name = "SderIssrId")
    private String SderIssrId;

    //发起方账户所属机构标识
    @XmlElement(name = "SderAcctIssrId")
    private String SderAcctIssrId;

    //发起方账户所护机构名称-机构无需填写
    @XmlElement(name = "SderAcctIssrNm")
    private String SderAcctIssrNm;

    //协议号码
    @XmlElement(name = "SgnNo")
    private String SgnNo;

    public SderInf() {
    }

    public SderInf(String sderIssrId, String sderAcctIssrId) {
        SderIssrId = sderIssrId;
        SderAcctIssrId = sderAcctIssrId;
    }

    public static final class SderInfBuilder {
        private SderInf sderInf;

        private SderInfBuilder(String sderIssrId, String sderAcctIssrId) {
            sderInf = new SderInf(sderIssrId,sderAcctIssrId);
        }

        public static SderInfBuilder verifyInfoSderInf(String sderIssrId, String sderAcctIssrId) {
            return new SderInfBuilder(sderIssrId,sderAcctIssrId);
        }

        public SderInfBuilder SderIssrId(String SderIssrId) {
            sderInf.setSderIssrId(SderIssrId);
            return this;
        }

        public SderInfBuilder SderAcctIssrId(String SderAcctIssrId) {
            sderInf.setSderAcctIssrId(SderAcctIssrId);
            return this;
        }

        public SderInfBuilder SderAcctIssrNm(String SderAcctIssrNm) {
            sderInf.setSderAcctIssrNm(SderAcctIssrNm);
            return this;
        }

        public SderInfBuilder SgnNo(String SgnNo) {
            sderInf.setSgnNo(SgnNo);
            return this;
        }

        public SderInf build() {
            return sderInf;
        }
    }


}
