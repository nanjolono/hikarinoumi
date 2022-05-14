package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PyerInf {

    //付款方账户 Max34Text 关联交易为快捷类交易时必送
    @XmlElement(name = "PyerAcctId")
    private String PyerAcctId;

    //付款方名称 Max60Text PyerNm
    @XmlElement(name = "PyerNm")
    private String PyerNm;

    //付款方证件类型 IDTpCd 证件号和证件类型应同时出现或不出现
    @XmlElement(name = "IDTp")
    private String IDTp;

    //付款方证件号  Max20Text 证件号和证件类型应同时出现或不出现
    @XmlElement(name = "IDNo")
    private String IDNo;

    //付款方预留手 Max18Text
    @XmlElement(name = "MobNo")
    private String MobNo;

    @XmlElement(name = "PyerAcctIssrId")
    private String PyerAcctIssrId;

    @XmlElement(name = "PyeeIssrId")
    private String PyeeIssrId;

    @XmlElement(name = "AuthMsg")
    private String AuthMsg;

    @XmlElement(name = "Smskey")
    private String Smskey ;


    public static final class PyerInfBuilder {
        private PyerInf pyerInf;

        private PyerInfBuilder() {
            pyerInf = new PyerInf();
        }

        public static PyerInfBuilder aPyerInf() {
            return new PyerInfBuilder();
        }

        public PyerInfBuilder PyerAcctId(String PyerAcctId) {
            pyerInf.setPyerAcctId(PyerAcctId);
            return this;
        }

        public PyerInfBuilder PyerNm(String PyerNm) {
            pyerInf.setPyerNm(PyerNm);
            return this;
        }

        public PyerInfBuilder IDTp(String IDTp) {
            pyerInf.setIDTp(IDTp);
            return this;
        }

        public PyerInfBuilder IDNo(String IDNo) {
            pyerInf.setIDNo(IDNo);
            return this;
        }

        public PyerInfBuilder MobNo(String MobNo) {
            pyerInf.setMobNo(MobNo);
            return this;
        }

        public PyerInfBuilder PyerAcctIssrId(String PyerAcctIssrId) {
            pyerInf.setPyerAcctIssrId(PyerAcctIssrId);
            return this;
        }

        public PyerInfBuilder PyeeIssrId(String PyeeIssrId) {
            pyerInf.setPyeeIssrId(PyeeIssrId);
            return this;
        }

        public PyerInfBuilder AuthMsg(String AuthMsg) {
            pyerInf.setAuthMsg(AuthMsg);
            return this;
        }

        public PyerInfBuilder Smskey(String Smskey) {
            pyerInf.setSmskey(Smskey);
            return this;
        }

        public PyerInf build() {
            return pyerInf;
        }
    }
}
