package com.nanjolono.payment.bean.request;

import com.nanjolono.payment.bean.request.subbody.*;
import com.nanjolono.payment.bean.request.subbody.OrdrInf;
import com.nanjolono.payment.security.certification.RsaCertUtils;
import com.nanjolono.payment.security.cupsec.CupSec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;

class ContractTest {

    private static final String path = ClassLoader.getSystemResource("").getPath();



    @Test
    @DisplayName("1 - 无卡快捷支付 - 认证支付触发短信 - 账户验证")
    void xmlStr() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //获取MsgBody
        QuickPayment response = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("0001").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.AcctInfoVerifyBuilder
                        .verifyInfo("300001","111011")
                        .bizAssInf(new BizAssInf())
                        .bizTp("300001")
                        .BizFunc("111011")
                        .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf("123123123", "张三")
                                .IDNo("220402199409080818").IDTp("01").MobNo("17627890921").build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0409PLMN9012ER45", getNow()).build())
                        .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("123123", "1231232").build()
                        ).build()
        ).build();
        //组装报文
        post(marshaller, response,"0001");
    }

    @Test
    @DisplayName("2 - 无卡快捷支付 - 认证支付触发短信 - 全额消费")
    void xmlStr1() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        QuickPayment response = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("0201").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.AcctInfoVerifyBuilder
                        .verifyInfo("300001","111011")
                        .bizAssInf(new BizAssInf())
                        .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf("123123123", "张三")
                                .IDNo("220402199409080818").IDTp("01").MobNo("17627890921").build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0409PLMN9012ER45", getNow()).build())
                        .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("123123", "1231232").build()
                        ).build()
        ).build();
        post(marshaller, response,"0201");
    }


    @Test
    @DisplayName("3 - 无卡快捷支付 - 全额消费 - 成功 - 协议支付")
    void xmlStr2() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111011");
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY100.00");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctId("22020930101019292");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        ChannelIssInf channelIssInf = new ChannelIssInf();
        channelIssInf.setChannelIssrId("01ADS523123");
        channelIssInf.setSgnNo("UPW0ISS001PDLQ90OD01005709200W0ISS001202205010000000002");
        contract.setChannelIssInf(channelIssInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
       // //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }

    @Test
    @DisplayName("4 - 退货")
    public void refund() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1101");
        //获取MsgBody
        Contract contract = getContract();

        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY35.00");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);

        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);

        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123123");
        contract.setPyeeInf(pyeeInf);

        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctIssrId("12312312");
        pyerInf.setPyeeIssrId("1231232");
        contract.setPyerInf(pyerInf);

        OriTrxInf oriTrxInf = new OriTrxInf();
        oriTrxInf.setOriTrxId("0405898KPLQ09182");
        oriTrxInf.setOriTrxAmt("CNY100.00");
        oriTrxInf.setOriTrxTp("1001");
        oriTrxInf.setOriOrdrId("123123123");
        oriTrxInf.setOriBizFunc("111011");
        oriTrxInf.setOriTrxDtTm("2020-09-09T18:14:12");
        contract.setOriTrxInf(oriTrxInf);

        //init(response, msgHeader, contract);
        post(marshaller, response,"1101");
    }


    @Test
    @DisplayName("5 - 无卡快捷支付 - 全额消费 - 超时 - 原交易")
    void xmlStr3() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111011");
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY0.33");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctId("22020930101019292");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        ChannelIssInf channelIssInf = new ChannelIssInf();
        channelIssInf.setChannelIssrId("01ADS523123");
        channelIssInf.setSgnNo("UPW0ISS001PDLQ90OD01005709200W0ISS001202205010000000002");
        contract.setChannelIssInf(channelIssInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }

    @Test
    @DisplayName("6 - 交易查询")
    void xmlStr4() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("3101");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111011");
        contract.setBizTp("300002");
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405899KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        contract.setTrxInf(trxInf);
        OriTrxInf oriTrxInf = new OriTrxInf();
        oriTrxInf.setOriTrxTp("1001");
        oriTrxInf.setOriBizTp("300001");
        oriTrxInf.setOriTrxDtTm("2020-09-09T18:14:12");
        oriTrxInf.setOriTrxId("0405898KPLQ09182");
        contract.setOriTrxInf(oriTrxInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"3101");
    }


    @Test
    @DisplayName("7 - 无卡快捷支付 - 分期消费")
    void xmlStr6() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("112011");
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY0.33");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctId("22020930101019292");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        ChannelIssInf channelIssInf = new ChannelIssInf();
        channelIssInf.setChannelIssrId("01ADS523123");
        channelIssInf.setSgnNo("UPW0ISS001PDLQ90OD01005709200W0ISS001202205010000000002");
        contract.setChannelIssInf(channelIssInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        BizAssInf bizAssInf = new BizAssInf();
        bizAssInf.setIPMode("0");
        bizAssInf.setIPNum("12");
        contract.setBizAssInf(bizAssInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }


    @Test
    @DisplayName("8 - 无卡快捷支付 - 预授权")
    void xmlStr7() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111021");
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY0.33");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctId("22020930101019292");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        ChannelIssInf channelIssInf = new ChannelIssInf();
        channelIssInf.setChannelIssrId("01ADS523123");
        channelIssInf.setSgnNo("UPW0ISS001PDLQ90OD01005709200W0ISS001202205010000000002");
        contract.setChannelIssInf(channelIssInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }


    @Test
    @DisplayName("9 - 无卡快捷支付 - 退货 - 预授权撤销")
    void xmlStr9() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1101");
        //获取MsgBody
        Contract contract = getContract();

        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY0.33");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);

        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);

        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123123");
        contract.setPyeeInf(pyeeInf);

        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctIssrId("12312312");
        pyerInf.setPyeeIssrId("1231232");
        contract.setPyerInf(pyerInf);

        OriTrxInf oriTrxInf = new OriTrxInf();
        oriTrxInf.setOriTrxId("0405898KPLQ09182");
        oriTrxInf.setOriTrxAmt("CNY0.33");
        oriTrxInf.setOriTrxTp("1001");
        oriTrxInf.setOriOrdrId("123123123");
        oriTrxInf.setOriBizFunc("111021");
        oriTrxInf.setOriTrxDtTm("2020-09-09T18:14:12");
        contract.setOriTrxInf(oriTrxInf);

        //init(response, msgHeader, contract);
        post(marshaller, response,"1101");
    }


    @Test
    @DisplayName("10 - 无卡快捷支付 - 预授权")
    void xmlStr10() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111021");
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY100.00");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctId("22020930101019292");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        ChannelIssInf channelIssInf = new ChannelIssInf();
        channelIssInf.setChannelIssrId("01ADS523123");
        channelIssInf.setSgnNo("UPW0ISS001PDLQ90OD01005709200W0ISS001202205070000000015");
        contract.setChannelIssInf(channelIssInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }


    @Test
    @DisplayName("11 - 无卡快捷支付 - 预授权完成")
    void xmlStr11() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111031");
        BizAssInf bizAssInf = new BizAssInf();
        bizAssInf.setPreAuthId("");
        bizAssInf.setOriTrxId("0405898KPLQ09182");
        contract.setBizAssInf(bizAssInf);
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09182");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY100.00");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctId("22020930101019292");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        ChannelIssInf channelIssInf = new ChannelIssInf();
        channelIssInf.setChannelIssrId("01ADS523123");
        channelIssInf.setSgnNo("UPW0ISS001PDLQ90OD01005709200W0ISS001202205070000000015");
        contract.setChannelIssInf(channelIssInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }

    @Test
    @DisplayName("12 - 预授权完成退款")
    void xmlStr12() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1101");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("411011");
        OriTrxInf oriTrxInf = new OriTrxInf();
        oriTrxInf.setOriBizFunc("111031");
        oriTrxInf.setOriTrxId("0405898KPLQ09182");
        oriTrxInf.setOriTrxAmt("CNY100.00");
        oriTrxInf.setOriOrdrId("123123123");
        oriTrxInf.setOriTrxDtTm("2020-09-09T18:14:12");
        oriTrxInf.setOriTrxTp("1001");
        oriTrxInf.setOriBizFunc("111031");
        contract.setOriTrxInf(oriTrxInf);
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxId("0405898KPLQ09181");
        trxInf.setTrxDtTm("2020-09-09T18:14:13");
        trxInf.setTrxAmt("CNY100.00");
        trxInf.setTrxTrmTp("08");
        contract.setTrxInf(trxInf);
        PyerInf pyerInf = new PyerInf();
        pyerInf.setPyerAcctIssrId("12312312");
        pyerInf.setPyeeIssrId("123123");
        contract.setPyerInf(pyerInf);
        PyeeInf pyeeInf = new PyeeInf();
        pyeeInf.setPyeeIssrId("123123");
        pyeeInf.setPyeeAcctIssrId("asdasds");
        contract.setPyeeInf(pyeeInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1101");
    }

    @Test
    @DisplayName("13 - 协议支付 付款成功")
    void xmlStr13() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("0003");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("311011");
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"0003");
    }

    @Test
    @DisplayName("全额消费 成功")
    void xmlStr17() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1002");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111011");
/*        RcverInf rcverInf = RcverInf.builder()
                .RcverAcctIssrId("W0ISS001")
                .RcverAcctTp("01")
                .Smskey("20220509090555000022")
                .RcverAcctId("HW73491JDUEHDF7829DJEF92838388EID").build();*/
        //contract.setRcverInf(rcverInf);
        MrchntInf mrchntInf = new MrchntInf();
        mrchntInf.setMrchntTpId("0209");
        mrchntInf.setMrchntNo("333T12F222D3123");
        mrchntInf.setMrchntPltfrmNm("123123123");
        contract.setMrchntInf(mrchntInf);
        OrdrInf ordrInf = new OrdrInf();
        ordrInf.setOrdrId("123123123");
        contract.setOrdrInf(ordrInf);
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxTrmTp("02");
        trxInf.setTrxId("1231231231");
        trxInf.setTrxDtTm("2020-09-09T18:14:12");
        trxInf.setTrxAmt("CNY100.00");
        contract.setTrxInf(trxInf);
        //init(response, msgHeader, contract);
        post(marshaller, response,"1002");
    }
    @Test
    @DisplayName("0 - 模板")
    void xmlStr0() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //实例化报文主体
        QuickPayment response = new QuickPayment();
        //获取MsgHeader
        MsgHeader msgHeader = getMsgHeader();
        msgHeader.setTrxtyp("1001");
        //获取MsgBody
        Contract contract = getContract();
        contract.setBizFunc("111031");


        //init(response, msgHeader, contract);
        post(marshaller, response,"1001");
    }


    private void post(Marshaller marshaller, QuickPayment response,String msgTp) throws JAXBException, IllegalAccessException {
        //JAVABean转XMLString
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(response,stringWriter);
        String src = stringWriter.toString();
        //加签
        String s = getSign(src);
        Sign sign1 = new Sign();
        sign1.setSignContext(s);
        String df = src + sign1.getSignResult();
        //发送
        postForEntity(df,msgTp);
    }

    private Contract getContract() {
        //Body
        Contract contract = new Contract();
        contract.setBizTp("300001");
        contract.setBizFunc("111011");
        //Trx
        TrxInf trxInf = new TrxInf();
        trxInf.setTrxDtTm("2022-04-22T12:12:12");
        trxInf.setTrxAmt("CNY100000.00");
        trxInf.setTrxId("0422PDKAYFPW9D3D");
        contract.setTrxInf(trxInf);

/*        RcverInf rcverInf = RcverInf.builder()
                .RcverAcctId("HW73491JDUEHDF7829DJEF92838388EID")
                .RcverNm("张三")
                .IDTp("01")
                .IDNo("220403199002090876")
                .MobNo("17887659042").build();*/
        //contract.setRcverInf(rcverInf);

        SderInf sderInf = new SderInf();
        sderInf.setSderIssrId("YUDJQ78943D");
        sderInf.setSderAcctIssrId("PDLQ90ODUS9");
        contract.setSderInf(sderInf);
        return contract;
    }

    private void postForEntity(String df,String msgTp) {
        //发送报文
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Length", String.valueOf(df.length()));
        httpHeaders.set("Content-Type", "application/xml;charset=utf-8");
        httpHeaders.set("MsgTp",msgTp);
        httpHeaders.set("OriIssrId","05709200");
        HttpEntity<String> httpEntity = new HttpEntity<>(df, httpHeaders);
        restTemplate.postForEntity("http://172.16.151.131/", httpEntity, String.class);
    }

    private String getSign(String src) {
        //加签
        String pfxkeyfile, keypwd, type;
        pfxkeyfile = path + "/eb882f6ef7f8a31c4d682611d31b8584b5030f83-sign.pfx";
        keypwd = "11111111";
        type = "PKCS12";

        PrivateKey privateKey = RsaCertUtils.getPriKeyPkcs12(pfxkeyfile, keypwd, type);

        String s = CupSec.rsaSignWithSha256(privateKey, src.getBytes());
        return s;
    }

    private MsgHeader getMsgHeader() {
        //Header
        MsgHeader msgHeader = new MsgHeader();

        msgHeader.setMsgVer("1000");
        msgHeader.setDrctn("11");
        msgHeader.setIssrId("05709200");
        msgHeader.setSignSN("4038186043");
        msgHeader.setSndDt("2022-04-22T12:12:12");
        msgHeader.setSignEncAlgo("0");
        msgHeader.setTrxtyp("0001");
        msgHeader.setMDAlgo("0");
        msgHeader.setEncSN("4022714199");
        msgHeader.setEncKey("11111111");
        msgHeader.setEncAlgo("0");
        return msgHeader;
    }

    private Marshaller getMarshaller() throws JAXBException {
        //准备报文
        JAXBContext context = JAXBContext.newInstance(QuickPayment.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);
        return marshaller;
    }

    private String getNow(){
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf2.format(date);
    }
}