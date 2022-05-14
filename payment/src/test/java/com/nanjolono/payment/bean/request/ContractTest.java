package com.nanjolono.payment.bean.request;

import com.nanjolono.payment.bean.MsgHeader;
import com.nanjolono.payment.bean.QuickPayment;
import com.nanjolono.payment.bean.dto.QuickPayDTO;
import com.nanjolono.payment.bean.request.subbody.*;
import com.nanjolono.payment.bean.request.subbody.OrdrInf;
import com.nanjolono.payment.constant.QuickPayConstant;
import com.nanjolono.payment.security.certification.RsaCertUtils;
import com.nanjolono.payment.security.cupsec.CupSec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class ContractTest {

    private static final String path = ClassLoader.getSystemResource("").getPath();



    @Test
    @DisplayName("1 - 无卡快捷支付 - 认证支付触发短信 - 账户验证")
    void xmlStr() throws JAXBException, IllegalAccessException {
        new QuickPayDTO();
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        //获取MsgBody
        QuickPayment response = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp(QuickPayConstant.TrxTpCd.AccountInfo.CONTRACT)
                        .mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder
                        .verifyInfo("300001","111011")
                        .bizAssInf(new BizAssInf())
                        .bizTp("300001")
                        .BizFunc(QuickPayConstant.BizFuncCd.fullConsumption)
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
                        .signEncAlgo("0").trxtyp("0201").mDAlgo("0")
                        .encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder
                        .verifyInfo("300001","111011")
                        .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf("123123123", "张三")
                                .IDNo("220402199409080818")
                                .IDTp("01")
                                .MobNo("17627890921")
                                .AuthMsg("123456")
                                .Smskey("20220513101016000062").build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0409PLMN9012ER45", getNow()).build())
                        .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("1231D312312", "1231232DQW1").build())
                        .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf().OriTrxId("0409PLMN9012ER45").build())
                        .build()
        ).build();
        post(marshaller, response,"0201");
    }


    @Test
    @DisplayName("3 - 无卡快捷支付 - 全额消费 - 成功 - 协议支付")
    void xmlStr2() throws JAXBException, IllegalAccessException {
        QuickPayment build = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000").drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12").signEncAlgo("0").mDAlgo("0")
                        .encSN("4022714199").encKey("11111111").encAlgo("0").trxtyp(QuickPayConstant.TrxTpCd.Debit.TRANS_PROTOCOL)
                        .build(),
                Contract.ContractBuilder.verifyInfo("300001", QuickPayConstant.BizFuncCd.fullConsumption)
                        .bizAssInf(BizAssInf.BizAssInfBuilder.aBizAssInf()
                                .IPMode("0").IPNum("12")
                                .build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09182", getNow())
                                .TrxAmt("CNY400.00")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("22020930101019292")
                                .build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                .PyeeIssrId("123123")
                                .PyeeAcctIssrId("asdasd")
                                .build())
                        .channelIssInf(ChannelIssInf.ChannelIssInfBuilder.aChannelIssInf()
                                .ChannelIssrId("01ADS523123")
                                .SgnNo("UPW0ISS0011231232D01005709200W0ISS001202205130000000063")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntTpId("0209")
                                .MrchntNo("333T12F222D3123")
                                .MrchntPltfrmNm("123123123")
                                .build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf()
                                .OrdrId("123123123")
                                .build())
                        .build()
        ).build();
        post(getMarshaller(), build,
                "1001");
    }

    @Test
    @DisplayName("4 - 退货")
    public void refund() throws JAXBException, IllegalAccessException {
        //JAXB 准备
        Marshaller marshaller = getMarshaller();
        QuickPayment response = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000").drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12").signEncAlgo("0").mDAlgo("0")
                        .encSN("4022714199").encKey("11111111").encAlgo("0").trxtyp("1101")
                        .build(),Contract.ContractBuilder.verifyInfo("300001","111011")
                                .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09183",getNow())
                                        .TrxAmt("CNY35.00")
                                        .TrxDtTm(getNow())
                                        .TrxTrmTp("08")
                                        .build())
                                .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                        .MrchntTpId("0209")
                                        .MrchntNo("333T12F222D3123")
                                        .MrchntPltfrmNm("123123123")
                                        .build())
                                .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                        .PyeeIssrId("123123123")
                                        .build())
                                .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                        .PyerAcctIssrId("12312312121")
                                        .PyeeIssrId("12312312321")
                                        .build())
                                .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf()
                                        .OriTrxId("0405898KPLQ09182")
                                        .OriTrxAmt("CNY100.00")
                                        .OriTrxTp("1001")
                                        .OriOrdrId("123123123")
                                        .OriBizAssInf("111011")
                                        .OriBizFunc("111011")
                                        .OriTrxDtTm("2022-05-13T11:20:00")
                                        .build())
                        .build()
        ).build();
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
        post(getMarshaller(), QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000").drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12").signEncAlgo("0").mDAlgo("0")
                        .encSN("4022714199").encKey("11111111").encAlgo("0").trxtyp("1001")
                        .build(),
                Contract.ContractBuilder.verifyInfo("300001","112011")
                        .bizAssInf(BizAssInf.BizAssInfBuilder.aBizAssInf().IPNum("12").IPMode("0").build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09182",getNow())
                                .TrxAmt("CNY100.00")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("22020930101019292")
                                .build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                .PyeeIssrId("123123")
                                .PyeeAcctIssrId("asdasd")
                                .build())
                        .channelIssInf(ChannelIssInf.ChannelIssInfBuilder.aChannelIssInf()
                                .ChannelIssrId("01ADS523123")
                                .SgnNo("UPW0ISS0011231232D01005709200W0ISS001202205120000000051")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntTpId("0209")
                                .MrchntNo("333T12F222D3123")
                                .MrchntPltfrmNm("123123123")
                                .build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf()
                                .OrdrId("123123123")
                                .build())
                        .build()
        ).build(),"1001");
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
        oriTrxInf.setOriTrxId("0908JDQIEM29P8QW");
        oriTrxInf.setOriTrxAmt("CNY1000.00");
        oriTrxInf.setOriOrdrId("PLQOSLKD99222");
        oriTrxInf.setOriTrxDtTm("2022-05-11T11:10:54");
        oriTrxInf.setOriTrxTp("1002");
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
        response.setMsgHeader(msgHeader);
        response.setMsgBody(contract);
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
    @DisplayName("认证支付 - 短信认证功能 - 模板")
    void xmlStr0() throws JAXBException, IllegalAccessException {
        post(getMarshaller(), new QuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("0003").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder.verifyInfo("300001","111011")
                        .trxInf(
                                TrxInf.TrxInfBuilder
                                        .verifyInfoTrxInf("0908JDQIEM29P8QW",getNow())
                                        .TrxAmt("CNY1000.00").build()
                        )
                        .rcverInf(RcverInf.RcverInfBuilder
                                .verifyInfoRcverInf("123123123123","张三")
                                .IDTp("01")
                                .IDNo("220406198002212341")
                                .MobNo("17627899654")
                                .build()
                        ).sderInf(
                                SderInf.SderInfBuilder.verifyInfoSderInf("DDPP22331FR","PLQMNB123")
                                        .build()
                        ).mrchntInf(
                                MrchntInf.MrchntInfBuilder.aMrchntInf()
                                        .MrchntNo("PLQD0923412KLQ4").MrchntTpId("0403").MrchntPltfrmNm("张三零食店").build()
                        ).build()
        ),"0003");
    }

    @Test
    @DisplayName("7.2.2 全额消费 成功")
    void xmlStr17() throws JAXBException, IllegalAccessException {
        post(getMarshaller(), new QuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("1002").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder.verifyInfo("100002","111011")
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("123123123123",getNow())
                                .TrxAmt("CNY1000.00")
                                .TrxDtTm(getNow())
                                .TrxId("0908JDQIEM29P8QW")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("PLQMNB123")
                                .AuthMsg("m1111")
                                .Smskey("20220511161023000041")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntNo("PLQD0923412KLQ4").MrchntTpId("0403").MrchntPltfrmNm("张三零食店").build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf().OrdrId("PLQOSLKD99222").build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf().PyeeIssrId("1231231233").PyeeAcctIssrId("123123123").build())
                        .build()
        ),"1002");
    }

    @Test
    @DisplayName("7.2.2 分期消费 成功")
    void xmlStr18() throws JAXBException, IllegalAccessException {
        post(getMarshaller(), new QuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("1002").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder.verifyInfo("100002","112011")
                        .bizAssInf(BizAssInf.BizAssInfBuilder.aBizAssInf().IPNum("03").IPMode("0").build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("123123123123",getNow())
                                .TrxAmt("CNY1000.00")
                                .TrxDtTm(getNow())
                                .TrxId("0908JDQIEM29P8QW")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("PLQMNB123")
                                .AuthMsg("m1111")
                                .Smskey("20220511161059000043")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntNo("PLQD0923412KLQ4").MrchntTpId("0403").MrchntPltfrmNm("张三零食店").build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf().OrdrId("PLQOSLKD99222").build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf().PyeeIssrId("1231231233").PyeeAcctIssrId("123123123").build())
                        .build()
        ),"1002");
    }


    @Test
    @DisplayName("7.2.2 预授权消费 成功")
    void xmlStr19() throws JAXBException, IllegalAccessException {
        post(getMarshaller(), new QuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("1002").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder.verifyInfo("100002","111021")
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("123123123123",getNow())
                                .TrxAmt("CNY1000.00")
                                .TrxDtTm(getNow())
                                .TrxId("0908JDQIEM29P8QW")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("PLQMNB123")
                                .AuthMsg("m1111")
                                .Smskey("20220511161208000045")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntNo("PLQD0923412KLQ4").MrchntTpId("0403").MrchntPltfrmNm("张三零食店").build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf().OrdrId("PLQOSLKD99222").build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf().PyeeIssrId("1231231233").PyeeAcctIssrId("123123123").build())
                        .build()
        ),"1002");
    }


    @Test
    @DisplayName("7.2.2 预授权撤销 成功")
    void xmlStr20() throws JAXBException, IllegalAccessException {
        post(getMarshaller(), new QuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("1101").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder.verifyInfo("100002","111021")
                        .bizAssInf(BizAssInf.BizAssInfBuilder.aBizAssInf().PreAuthId("abc123").OriTrxId("0908JDQIEM29P8QW").build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("123123123123",getNow())
                                .TrxAmt("CNY1000.00")
                                .TrxDtTm(getNow())
                                .TrxId("0908JDQIEM29P8QW")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("PLQMNB123")
                                .AuthMsg("m1111")
                                .Smskey("20220511104911000031")
                                .build())
                        .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf()
                                .OriTrxAmt("CNY1000.00")
                                .OriTrxTp("1002")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntNo("PLQD0923412KLQ4")
                                .MrchntTpId("0403")
                                .MrchntPltfrmNm("张三零食店").build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf().OrdrId("PLQOSLKD99222").build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                .PyeeIssrId("1231231233")
                                .PyeeAcctIssrId("123123123")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctIssrId("123123123")
                                .PyeeIssrId("123123123")
                                .build())
                        .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf()
                                .OriTrxId("0908JDQIEM29P8QW")
                                .OriOrdrId("PLQOSLKD99222")
                                .OriBizFunc("111021")
                                .OriTrxDtTm("2022-05-11T10:50:33")
                                .OriTrxAmt("CNY1000.00")
                                .OriTrxTp("1002")
                                .build())
                        .build()
        ),"1101");
    }

    @Test
    @DisplayName("7.2.2 预授权完成 成功")
    void xmlStr21() throws JAXBException, IllegalAccessException {
        post(getMarshaller(), new QuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                        .drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12")
                        .signEncAlgo("0").trxtyp("1002").mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build(),
                Contract.ContractBuilder.verifyInfo("100002","111031")
                        .bizAssInf(BizAssInf.BizAssInfBuilder.aBizAssInf()
                                .PreAuthId("abc123")
                                .OriTrxId("0908JDQIEM29P8QW")
                                .build())
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("123123123123",getNow())
                                .TrxAmt("CNY1000.00")
                                .TrxDtTm(getNow())
                                .TrxId("0908JDQIEM29P8QW")
                                .TrxTrmTp("08")
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("PLQMNB123")
                                .AuthMsg("m1111")
                                .Smskey("20220511110723000034")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntNo("PLQD0923412KLQ4").MrchntTpId("0403").MrchntPltfrmNm("张三零食店").build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf().OrdrId("PLQOSLKD99222").build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf().PyeeIssrId("1231231233").PyeeAcctIssrId("123123123").build())
                        .build()
        ),"1002");
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

        SderInf sderInf = new SderInf();
        sderInf.setSderIssrId("YUDJQ78943D");
        sderInf.setSderAcctIssrId("PDLQ90ODUS9");
        contract.setSderInf(sderInf);
        return contract;
    }

    private void postForEntity(String df,String msgTp) throws IllegalAccessException {
        //发送报文
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Length", String.valueOf(df.length()));
        httpHeaders.set("Content-Type", "application/xml;charset=utf-8");
        httpHeaders.set("MsgTp",msgTp);
        httpHeaders.set("OriIssrId","05709200");
        HttpEntity<String> httpEntity = new HttpEntity<>(df, httpHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity("http://172.16.151.139/", httpEntity, String.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            throw new IllegalAccessException("通信异常");
        }
        System.out.println(result.getBody());
    }

    private String getSign(String src) {
        //加签
        String pfxkeyfile, keypwd, type;
        pfxkeyfile = path + "/eb882f6ef7f8a31c4d682611d31b8584b5030f83-sign.pfx";
        keypwd = "11111111";
        type = "PKCS12";
        PrivateKey privateKey = RsaCertUtils.getPriKeyPkcs12(pfxkeyfile, keypwd, type);
        return CupSec.rsaSignWithSha256(privateKey, src.getBytes());
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


    @Test
    void test() {
        String bi="<root>\n" +
                "    <MsgHeader>\n" +
                "        <MsgVer>1000</MsgVer>\n" +
                "        <SndDt>2022-04-22T12:12:12</SndDt>\n" +
                "        <Trxtyp>0001</Trxtyp>\n" +
                "        <IssrId>05709200</IssrId>\n" +
                "        <Drctn>11</Drctn>\n" +
                "        <SignSN>4038186043</SignSN>\n" +
                "        <EncSN>4022714199</EncSN>\n" +
                "        <EncKey>11111111</EncKey>\n" +
                "        <MDAlgo>0</MDAlgo>\n" +
                "        <SignEncAlgo>0</SignEncAlgo>\n" +
                "        <EncAlgo>0</EncAlgo>\n" +
                "    </MsgHeader>\n" +
                "    <MsgBody>\n" +
                "        <BizTp>300001</BizTp>\n" +
                "        <BizFunc>111011</BizFunc>\n" +
                "        <BizAssInf/>\n" +
                "        <TrxInf>\n" +
                "            <TrxId>0409PLMN9012ER45</TrxId>\n" +
                "            <TrxDtTm>2022-05-13T14:56:40</TrxDtTm>\n" +
                "        </TrxInf>\n" +
                "        <RcverInf>\n" +
                "            <RcverAcctId>123123123</RcverAcctId>\n" +
                "            <RcverNm>张三</RcverNm>\n" +
                "            <IDTp>01</IDTp>\n" +
                "            <IDNo>220402199409080818</IDNo>\n" +
                "            <MobNo>17627890921</MobNo>\n" +
                "        </RcverInf>\n" +
                "        <SderInf>\n" +
                "            <SderIssrId>123123</SderIssrId>\n" +
                "            <SderAcctIssrId>1231232</SderAcctIssrId>\n" +
                "        </SderInf>\n" +
                "    </MsgBody>\n" +
                "</root>{S:H4PH1ElgtR4/bfvfaoQPYsOWh/uyL4vOCFo1HRlQnt1EZSXx/VCUjixUO/EsVx8ZHA8TBwXBfa+jBHAZjb0ZZcOagwjwfR4C1SItY/fDHUfgWZXwtqVG2KpRdMDwEMIt+PSz8BwZAwKWAskVhte4iCPv9+PykOjHueiSgIS3DhqL8+w7JclbQkofJpti3py77XI2syMjnT/tMWkr+rRmhm3zeWbsnmaaFYSbEs+BUI+SlACOrn/vQuroFw8dj+N++DaHfbIW0yIsMrgNCT2A2ld1H8kCaKrBXn6UcMgDMXLOAI7UmwTOQ01DiYE+UL1NciAkyJLvs4VKk557MhxCXA==}";
        String regex = "<root>*</root>";
        System.out.println(bi.replaceAll(regex,""));
    }
}