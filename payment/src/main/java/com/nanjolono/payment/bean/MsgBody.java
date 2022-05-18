package com.nanjolono.payment.bean;

import com.nanjolono.payment.bean.request.subbody.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgBody {


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
}
