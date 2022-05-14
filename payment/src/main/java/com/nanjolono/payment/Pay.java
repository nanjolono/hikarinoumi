package com.nanjolono.payment;

import com.nanjolono.payment.bean.request.Contract;
import com.nanjolono.payment.bean.MsgHeader;
import com.nanjolono.payment.bean.QuickPayment;
import com.nanjolono.payment.bean.request.subbody.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Pay {

    public static QuickPayment pay(String money,String SgnNo) {
        return QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000").drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12").signEncAlgo("0").mDAlgo("0")
                        .encSN("4022714199").encKey("11111111").encAlgo("0").trxtyp("1001")
                        .build(),
                Contract.ContractBuilder.verifyInfo("300001","111011")
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09182",getNow())
                                .TrxAmt(money)
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
                                .SgnNo(SgnNo)
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
    }

    public static QuickPayment refund(String money,String SgnNo) {
        return QuickPayment.QuickPaymentBuilder.aQuickPayment(
                MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000").drctn("11").issrId("05709200")
                        .signSN("4038186043").sndDt("2022-04-22T12:12:12").signEncAlgo("0").mDAlgo("0")
                        .encSN("4022714199").encKey("11111111").encAlgo("0").trxtyp("1001")
                        .build(),
                Contract.ContractBuilder.verifyInfo("300001","111011")
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09182",getNow())
                                .TrxAmt(money)
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
                                .SgnNo(SgnNo)
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
    }
    private static String getNow(){
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf2.format(date);
    }
}
