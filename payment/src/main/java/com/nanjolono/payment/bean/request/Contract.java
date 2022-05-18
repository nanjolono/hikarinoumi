package com.nanjolono.payment.bean.request;

import com.nanjolono.payment.bean.MsgBody;
import com.nanjolono.payment.bean.request.subbody.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 无卡快捷支付 - 认证支付触发短信
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract extends MsgBody {

    //业务种类
    @XmlElement(name = "BizTp")
    private String bizTp;

    //业务功能
    @XmlElement(name = "BizFunc")
    private String BizFunc;

    //业务辅助信息
    @XmlElement(name = "BizAssInf")
    private BizAssInf bizAssInf;

    //交易信息
    @XmlElement(name = "TrxInf")
    private TrxInf trxInf;

    //接收方信息
    @XmlElement(name = "RcverInf")
    private RcverInf rcverInf;

    //敏感信息
    //@TODO询问银联
    @XmlElement(name = "SensInf")
    private SensInf sensInf;

    //发起方信息
    @XmlElement(name = "SderInf")
    private SderInf sderInf;

    //原交易信息
    @XmlElement(name = "OriTrxInf")
    private OriTrxInf oriTrxInf;

    @XmlElement(name = "PyerInf")
    private PyerInf pyerInf;

    //收款方/发起方
    @XmlElement(name = "PyeeInf")
    private PyeeInf pyeeInf;

    //渠道方信息
    @XmlElement(name = "ChannelIssrInf")
    private ChannelIssInf channelIssInf;


    //单位结算卡信息
    @XmlElement(name = "CorpCard")
    private CorpCard corpCard;

    //产品信息
    @XmlElement(name = "ProductInfo")
    private ProductInfo productInfo;

    //订单信息
    @XmlElement(name = "OrdrInf")
    private OrdrInf ordrInf;
    //商户信息
    @XmlElement(name = "MrchntInf")
    private MrchntInf mrchntInf;

    //商户信息
    @XmlElement(name = "SubMrchntInf")
    private SubMrchntInf subMrchntInf;

    //风险监控信息
    @XmlElement(name = "RskInf")
    private RskInf rskInf;

    //风险监控信息
    @XmlElement(name = "EncrpAssInf")
    private EncrpAssInf encrpAssInf;

    @XmlElement(name = "SysRtnInf")
    private SysRtnInf sysRtnInf;

    @XmlElement(name = "BizInf")
    private BizInf bizInf;

    public Contract(String bizTp, String bizFunc) {
        this.bizTp = bizTp;
        BizFunc = bizFunc;
    }

    public static final class ContractBuilder {
        private Contract contract;

        private ContractBuilder(String bizTp, String bizFunc) {
            contract = new Contract(bizTp,bizFunc);
        }
        private ContractBuilder(){

        }
        public static ContractBuilder aContract() {
            return new ContractBuilder();
        }

        public static ContractBuilder verifyInfo(String bizTp, String bizFunc){
            return new ContractBuilder(bizTp,bizFunc);
        }
        public ContractBuilder bizTp(String bizTp) {
            contract.setBizTp(bizTp);
            return this;
        }

        public ContractBuilder BizFunc(String BizFunc) {
            contract.setBizFunc(BizFunc);
            return this;
        }

        public ContractBuilder bizAssInf(BizAssInf bizAssInf) {
            contract.setBizAssInf(bizAssInf);
            return this;
        }

        public ContractBuilder trxInf(TrxInf trxInf) {
            contract.setTrxInf(trxInf);
            return this;
        }

        public ContractBuilder rcverInf(RcverInf rcverInf) {
            contract.setRcverInf(rcverInf);
            return this;
        }

        public ContractBuilder sensInf(SensInf sensInf) {
            contract.setSensInf(sensInf);
            return this;
        }

        public ContractBuilder sderInf(SderInf sderInf) {
            contract.setSderInf(sderInf);
            return this;
        }

        public ContractBuilder oriTrxInf(OriTrxInf oriTrxInf) {
            contract.setOriTrxInf(oriTrxInf);
            return this;
        }

        public ContractBuilder pyerInf(PyerInf pyerInf) {
            contract.setPyerInf(pyerInf);
            return this;
        }

        public ContractBuilder pyeeInf(PyeeInf pyeeInf) {
            contract.setPyeeInf(pyeeInf);
            return this;
        }

        public ContractBuilder channelIssInf(ChannelIssInf channelIssInf) {
            contract.setChannelIssInf(channelIssInf);
            return this;
        }

        public ContractBuilder corpCard(CorpCard corpCard) {
            contract.setCorpCard(corpCard);
            return this;
        }

        public ContractBuilder productInfo(ProductInfo productInfo) {
            contract.setProductInfo(productInfo);
            return this;
        }

        public ContractBuilder ordrInf(OrdrInf ordrInf) {
            contract.setOrdrInf(ordrInf);
            return this;
        }

        public ContractBuilder mrchntInf(MrchntInf mrchntInf) {
            contract.setMrchntInf(mrchntInf);
            return this;
        }

        public ContractBuilder subMrchntInf(SubMrchntInf subMrchntInf) {
            contract.setSubMrchntInf(subMrchntInf);
            return this;
        }

        public ContractBuilder rskInf(RskInf rskInf) {
            contract.setRskInf(rskInf);
            return this;
        }

        public ContractBuilder encrpAssInf(EncrpAssInf encrpAssInf) {
            contract.setEncrpAssInf(encrpAssInf);
            return this;
        }



        public ContractBuilder sysRtnInf(SysRtnInf sysRtnInf) {
            contract.setSysRtnInf(sysRtnInf);
            return this;
        }

        public Contract build() {
            return contract;
        }
    }
}
