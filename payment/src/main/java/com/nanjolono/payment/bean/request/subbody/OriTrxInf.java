package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class OriTrxInf {

    //原交易流水号 原验证交易的请求流水号，仅限字母和数字
    @XmlElement(name = "OriTrxId")
    private String OriTrxId;

    //原支付交易的交易金额 AmountText 原支付交易的交易金额
    @XmlElement(name = "OriTrxAmt")
    private String OriTrxAmt;

    //原订单号 Max40Text 应填写原始订单号，仅限字母和数字
    @XmlElement(name = "OriOrdrId")
    private String OriOrdrId;

    //原交易日期 ISODateTime 原始交易发起的日期时间
    @XmlElement(name = "OriTrxDtTm")
    private String OriTrxDtTm;

    //原交易类型 TrxTpCd 填写原交易的“交易类型”
    @XmlElement(name = "OriTrxTp")
    private String OriTrxTp;

    //原业务功能 BizFuncCd 该字段用于标识原交易的业务功能，按原交易填写信息原样上送
    @XmlElement(name = "OriBizFunc")
    private String OriBizFunc;

    //原业务辅助信息 按原交易填写信息原样上送
    @XmlElement(name = "OriBizAssInf")
    private String OriBizAssInf;

    //原产品类型 MaxMin8Text 发起方可选，银联必校验并补 填给接收方，默认00000000。具体取值由不同业务产品定义
    @XmlElement(name = "ProductTp")
    private String ProductTp;

    //原产品辅助信息 Max120Text 具体用法取值与产品类型保持一致，由不同业务产品定义
    @XmlElement(name = "ProductAssInformation")
    private String ProductAssInformation;

    @XmlElement(name = "OriBizTp")
    private String OriBizTp;

}
