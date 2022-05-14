package com.nanjolono.payment.bean.request.subbody;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class RcverInf {

    //接收方账户所属标识-银联填写，机构无需填写
    @XmlElement(name = "RcverAcctIssrId")
    private String RcverAcctIssrId;

    //持卡人账号
    @XmlElement(name = "RcverAcctId")
    private String RcverAcctId;

    //接收方账户类型
    @XmlElement(name = "RcverAcctTp")
    private String RcverAcctTp;

    //接收方名称
    @XmlElement(name = "RcverNm")
    private String RcverNm;

    //接收方证件类型
    @XmlElement(name = "IDTp")
    private String IDTp;

    //接收方证件号
    @XmlElement(name = "IDNo")
    private String IDNo;

    //接收方预留手机号
    @XmlElement(name = "MobNo")
    private String MobNo;

    //动态验证码 用于验证付款方身份，仅限字母和数字。发起方必须将该信息上送给银联系统，银联系统根据短信触发模式可选透传至接收方
    @XmlElement(name = "AuthMsg")
    private String AuthMsg;

    //持卡人账户等级
    @XmlElement(name = "AcctLvl")
    private String AcctLvl;

    //动态短信关联码 与原触发短信交易的动态短信关联码保持一致
    @XmlElement(name = "Smskey")
    private String Smskey;

    @XmlElement(name = "IssrChnnlUrl")
    private String IssrChnnlUrl;

    @XmlElement(name = "IssrChnnlId")
    private String IssrChnnlId;
    public RcverInf(String rcverAcctId, String rcverNm) {
        RcverAcctId = rcverAcctId;
        RcverNm = rcverNm;
    }

    public RcverInf() {
    }

    public static final class RcverInfBuilder {
        private RcverInf rcverInf;

        private RcverInfBuilder() {
            rcverInf = new RcverInf();
        }

        private RcverInfBuilder(String rcverAcctId, String rcverNm) {
            rcverInf = new RcverInf(rcverAcctId,rcverNm);
        }

        public static RcverInfBuilder verifyInfoRcverInf(String rcverAcctId, String rcverNm) {
            return new RcverInfBuilder(rcverAcctId,rcverNm);
        }

        public RcverInfBuilder RcverAcctIssrId(String RcverAcctIssrId) {
            rcverInf.setRcverAcctIssrId(RcverAcctIssrId);
            return this;
        }

        public RcverInfBuilder RcverAcctId(String RcverAcctId) {
            rcverInf.setRcverAcctId(RcverAcctId);
            return this;
        }

        public RcverInfBuilder RcverAcctTp(String RcverAcctTp) {
            rcverInf.setRcverAcctTp(RcverAcctTp);
            return this;
        }

        public RcverInfBuilder RcverNm(String RcverNm) {
            rcverInf.setRcverNm(RcverNm);
            return this;
        }

        public RcverInfBuilder IDTp(String IDTp) {
            rcverInf.setIDTp(IDTp);
            return this;
        }

        public RcverInfBuilder IDNo(String IDNo) {
            rcverInf.setIDNo(IDNo);
            return this;
        }

        public RcverInfBuilder MobNo(String MobNo) {
            rcverInf.setMobNo(MobNo);
            return this;
        }

        public RcverInfBuilder AuthMsg(String AuthMsg) {
            rcverInf.setAuthMsg(AuthMsg);
            return this;
        }

        public RcverInfBuilder Smskey(String Smskey) {
            rcverInf.setSmskey(Smskey);
            return this;
        }

        public RcverInfBuilder AcctLvl(String AcctLvl) {
            rcverInf.setAcctLvl(AcctLvl);
            return this;
        }

        public RcverInf build() {
            return rcverInf;
        }
    }
}

