package com.nanjolono.payment.bean.request.subbody;


import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PyeeInf {

    //发送机构标识 - 发起机构发送交易的机构标识，仅限字母和数字
    @XmlElement(name = "PyeeIssrId")
    private String PyeeIssrId;

    //收款方所属机构标识 - 成员机构标识，仅限字母和数字
    @XmlElement(name = "PyeeAcctIssrId")
    private String PyeeAcctIssrId;

    //收款方账户号
    @XmlElement(name = "PyeeAcctId")
    private String PyeeAcctId;


    public static final class PyeeInfBuilder {
        private PyeeInf pyeeInf;

        private PyeeInfBuilder() {
            pyeeInf = new PyeeInf();
        }

        public static PyeeInfBuilder aPyeeInf() {
            return new PyeeInfBuilder();
        }

        public PyeeInfBuilder PyeeIssrId(String PyeeIssrId) {
            pyeeInf.setPyeeIssrId(PyeeIssrId);
            return this;
        }

        public PyeeInfBuilder PyeeAcctIssrId(String PyeeAcctIssrId) {
            pyeeInf.setPyeeAcctIssrId(PyeeAcctIssrId);
            return this;
        }

        public PyeeInfBuilder PyeeAcctId(String PyeeAcctId) {
            pyeeInf.setPyeeAcctId(PyeeAcctId);
            return this;
        }

        public PyeeInf build() {
            return pyeeInf;
        }
    }
}
