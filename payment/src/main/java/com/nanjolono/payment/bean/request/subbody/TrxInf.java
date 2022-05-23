package com.nanjolono.payment.bean.request.subbody;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TrxInf implements Serializable {

    //交易流水号
    @XmlElement(name = "TrxId")
    private String TrxId;

    //交易日期时间
    @XmlElement(name = "TrxDtTm")
    private String TrxDtTm;

    //清算日期-机构无需填写
    @XmlElement(name = "SettlmtDt")
    private String SettlmtDt;

    //清算信息-机构无需填写
    @XmlElement(name = "SettlmtInf")
    private String SettlmtInf;

    //交易金额
    @XmlElement(name = "TrxAmt")
    private String TrxAmt;

    //账户输入方式
    @XmlElement(name = "AcctInTp")
    private String AcctInTp;

    //交易终端类型  07-电脑 08-手机 10-平板设备 18-可穿戴设备 19-数字电视 00-其他
    @XmlElement(name = "TrxTrmTp")
    private String TrxTrmTp;

    //交易终端编码
    @XmlElement(name = "TrxTrmNo")
    private String TrxTrmNo;

    //发起方保留域
    @XmlElement(name = "AcqrReserved")
    private String AcqrReserved;

    public TrxInf(String trxId, String trxDtTm) {
        TrxId = trxId;
        TrxDtTm = trxDtTm;
    }

    public TrxInf() {
    }


    public static final class TrxInfBuilder {
        private TrxInf trxInf;

        private TrxInfBuilder() {
            trxInf = new TrxInf();
        }

        private TrxInfBuilder(String trxId, String trxDtTm) {
            trxInf = new TrxInf(trxId,trxDtTm);
        }

        public static TrxInfBuilder verifyInfoTrxInf(String trxId, String trxDtTm) {
            return new TrxInfBuilder(trxId,trxDtTm);
        }

        public TrxInfBuilder TrxId(String TrxId) {
            trxInf.setTrxId(TrxId);
            return this;
        }

        public TrxInfBuilder TrxDtTm(String TrxDtTm) {
            trxInf.setTrxDtTm(TrxDtTm);
            return this;
        }

        public TrxInfBuilder SettlmtDt(String SettlmtDt) {
            trxInf.setSettlmtDt(SettlmtDt);
            return this;
        }

        public TrxInfBuilder SettlmtInf(String SettlmtInf) {
            trxInf.setSettlmtInf(SettlmtInf);
            return this;
        }

        public TrxInfBuilder TrxAmt(String TrxAmt) {
            trxInf.setTrxAmt(TrxAmt);
            return this;
        }

        public TrxInfBuilder AcctInTp(String AcctInTp) {
            trxInf.setAcctInTp(AcctInTp);
            return this;
        }

        public TrxInfBuilder TrxTrmTp(String TrxTrmTp) {
            trxInf.setTrxTrmTp(TrxTrmTp);
            return this;
        }

        public TrxInfBuilder TrxTrmNo(String TrxTrmNo) {
            trxInf.setTrxTrmNo(TrxTrmNo);
            return this;
        }

        public TrxInfBuilder AcqrReserved(String AcqrReserved) {
            trxInf.setAcqrReserved(AcqrReserved);
            return this;
        }

        public TrxInf build() {
            return trxInf;
        }
    }
}
