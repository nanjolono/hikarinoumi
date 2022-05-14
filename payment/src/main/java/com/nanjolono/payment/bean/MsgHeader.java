package com.nanjolono.payment.bean;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class MsgHeader {

    //报文版本
    @XmlElement(name = "MsgVer")
    private String msgVer;

    //报文发起日期时间
    @XmlElement(name = "SndDt")
    private String sndDt;

    //交易类型
    @XmlElement(name = "Trxtyp")
    private String trxtyp;

    //发起方所属机构标识
    @XmlElement(name = "IssrId")
    private String issrId;

    //报文方向
    @XmlElement(name = "Drctn")
    private String drctn;

    //签名证书序列号
    @XmlElement(name = "SignSN")
    private String signSN;

    //加密证书序列号
    @XmlElement(name = "EncSN")
    private String encSN;

    //敏感信息对称加密密钥
    @XmlElement(name = "EncKey")
    private String encKey;

    //摘要算法类型 当签名及加密算法标识为 0(RSA 时，可使用以下算法: 0:SHA-256)当签名及加密算法标识为 1(SM2时，应使用以下算法: 1:SM3)
    @XmlElement(name = "MDAlgo")
    private String mDAlgo;

    //签名和密钥加密算法 MaxMin1Text 0:RSA 1:SM2
    @XmlElement(name = "SignEncAlgo")
    private String signEncAlgo;

    //对称加密算法类型 当签名及加密算法标识为 0(RSA 时，可使用以下算法: 0:3DES 当签名及加密算法标识为 1(SM2 时，应使用以下算法:1:SM4)
    @XmlElement(name = "EncAlgo")
    private String encAlgo;


    public static final class MsgHeaderBuilder {
        private MsgHeader msgHeader;

        private MsgHeaderBuilder() {
            msgHeader = new MsgHeader();
        }

        public static MsgHeaderBuilder aMsgHeader() {
            return new MsgHeaderBuilder();
        }

        public MsgHeaderBuilder msgVer(String msgVer) {
            msgHeader.setMsgVer(msgVer);
            return this;
        }

        public MsgHeaderBuilder sndDt(String sndDt) {
            msgHeader.setSndDt(sndDt);
            return this;
        }

        public MsgHeaderBuilder trxtyp(String trxtyp) {
            msgHeader.setTrxtyp(trxtyp);
            return this;
        }

        public MsgHeaderBuilder issrId(String issrId) {
            msgHeader.setIssrId(issrId);
            return this;
        }

        public MsgHeaderBuilder drctn(String drctn) {
            msgHeader.setDrctn(drctn);
            return this;
        }

        public MsgHeaderBuilder signSN(String signSN) {
            msgHeader.setSignSN(signSN);
            return this;
        }

        public MsgHeaderBuilder encSN(String encSN) {
            msgHeader.setEncSN(encSN);
            return this;
        }

        public MsgHeaderBuilder encKey(String encKey) {
            msgHeader.setEncKey(encKey);
            return this;
        }

        public MsgHeaderBuilder mDAlgo(String mDAlgo) {
            msgHeader.setMDAlgo(mDAlgo);
            return this;
        }

        public MsgHeaderBuilder signEncAlgo(String signEncAlgo) {
            msgHeader.setSignEncAlgo(signEncAlgo);
            return this;
        }

        public MsgHeaderBuilder encAlgo(String encAlgo) {
            msgHeader.setEncAlgo(encAlgo);
            return this;
        }

        public MsgHeader build() {
            return msgHeader;
        }
    }
}
