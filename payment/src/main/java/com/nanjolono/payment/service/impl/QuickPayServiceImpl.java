package com.nanjolono.payment.service.impl;


import com.nanjolono.payment.bean.MsgHeader;
import com.nanjolono.payment.bean.QuickPayment;
import com.nanjolono.payment.bean.dto.*;
import com.nanjolono.payment.bean.request.Contract;
import com.nanjolono.payment.bean.request.subbody.*;
import com.nanjolono.payment.constant.QuickPayConstant;
import com.nanjolono.payment.response.QrcodePayResponse;
import com.nanjolono.payment.security.certification.RsaCertUtils;
import com.nanjolono.payment.security.cupsec.CupSec;
import com.nanjolono.payment.service.QuickPayService;
import com.nanjolono.payment.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
public class QuickPayServiceImpl implements QuickPayService {

    private static final String path = ClassLoader.getSystemResource("").getPath();

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Object sign(SignDto signDto) throws JAXBException, IllegalAccessException {
        Marshaller marshaller = getMarshaller();
        QuickPayment entity = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                getMsgHeader(QuickPayConstant.TrxTpCd.AccountInfo.CONTRACT),
                Contract.ContractBuilder
                        .verifyInfo("300001","111011")
                        .bizAssInf(new BizAssInf())
                        .bizTp("300001")
                        .BizFunc(QuickPayConstant.BizFuncCd.fullConsumption)
                        .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf(signDto.getRcverAcct(), signDto.getRcverNm())
                                .IDNo(signDto.getIdNo()).IDTp(signDto.getIdTp()).MobNo(signDto.getMobNo()).build())
                        //@TODO交易ID随机
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0409PLMN9012ER45", getNow()).build())
                        //@TODO接收方
                        .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("123123", "1231232").build()
                        ).build()
        ).build();
        //组装报文
        QuickPayment result = postForEntity(sign(marshaller, entity), "0001");
        if (!"00000000".equals(Objects.requireNonNull(result.getMsgBody().getSysRtnInf()).getSysRtnCd())) {
            return null;
        }
        redisTemplate.opsForValue()
                .set(signDto.getIdNo()
                        ,result.getMsgBody().getRcverInf().getSmskey(),2,TimeUnit.DAYS);
        return QrcodePayResponse.success();
    }



    @Override
    public Object contract(ContractDto contractDto) throws JAXBException, IllegalAccessException {
        QuickPayment entity = QuickPayment.QuickPaymentBuilder.aQuickPayment(getMsgHeader(QuickPayConstant.TrxTpCd.SignTrans.CONTRACT_PAYMENT),Contract.ContractBuilder
                .verifyInfo("300001","111011")
                .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf(contractDto.getRcverAcct(), contractDto.getRcverNm())
                        .IDNo(contractDto.getIdNo())
                        .IDTp(contractDto.getIdTp())
                        .MobNo(contractDto.getMobNo())
                        .AuthMsg(contractDto.getAuthMsg())
                        .Smskey(Objects.requireNonNull(redisTemplate.opsForValue()
                                .get(contractDto.getIdNo())).toString()).build())
                .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0409PLMN9012ER45", getNow()).build())
                .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("1231D312312", "1231232DQW1").build())
                .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf().OriTrxId("0409PLMN9012ER45").build())
                .build()
        ).build();
        QuickPayment result = postForEntity(sign(getMarshaller(), entity), "0201");
        if (!"00000000".equals(Objects.requireNonNull(result.getMsgBody().getSysRtnInf()).getSysRtnCd())) {
            return R.error();
        }
        redisTemplate.opsForValue().set(contractDto.getRcverAcct(),result.getMsgBody().getBizInf().getSgnNo(),2, TimeUnit.DAYS);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",result);
        return QrcodePayResponse.success();
    }

    @Override
    public Object quickPay(QuickPayDTO qucikPayDto) throws JAXBException, IllegalAccessException {
        QuickPayment entity = QuickPayment.QuickPaymentBuilder.aQuickPayment(getMsgHeader(QuickPayConstant.TrxTpCd.Debit.TRANS_PROTOCOL),
                Contract.ContractBuilder.verifyInfo("300001", getBizFunc(qucikPayDto))
                        .bizAssInf(getBizAssInf(qucikPayDto))
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09182", getNow())
                                .TrxAmt(qucikPayDto.getAmount())
                                .TrxTrmTp(qucikPayDto.getTrxTp())
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId(qucikPayDto.getPyerAcctId())
                                .build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                .PyeeIssrId("123123") //接收方账号
                                .PyeeAcctIssrId("asdasd") //接收方账号
                                .build())
                        .channelIssInf(ChannelIssInf.ChannelIssInfBuilder.aChannelIssInf()
                                .ChannelIssrId("01ADS523123")  //渠道号
                                .SgnNo(
                                        Objects.requireNonNull(redisTemplate.opsForValue().get(qucikPayDto.getPyerAcctId())).toString()
                                )
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntTpId("0209")  //商户类型
                                .MrchntNo("333T12F222D3123")  //商户号
                                .MrchntPltfrmNm("123123123")  //商户名称
                                .build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf()
                                .OrdrId("123123123")
                                .build())
                        .build()).build();
        QuickPayment result = postForEntity(sign(getMarshaller(), entity), "1001");
        if (!"00000000".equals(Objects.requireNonNull(result.getMsgBody().getSysRtnInf()).getSysRtnCd())) {
            return R.error();
        }
        return R.ok();
    }

    @Override
    public Object refund(RefundDTO refundDTO) throws JAXBException, IllegalAccessException {
        return null;
    }

    @Override
    public Object queryOrder(QueryOrder queryOrder) throws JAXBException, IllegalAccessException {
        return null;
    }

    @Override
    public Object preAhtuCancel(QueryOrder queryOrder) throws JAXBException, IllegalAccessException {
        return null;
    }

    @Override
    public Object preAhtuDone(QueryOrder queryOrder) throws JAXBException, IllegalAccessException {
        return null;
    }

    private String getBizFunc(QuickPayDTO quickPayDTO) {
        if ("2".equals(quickPayDTO.getQucikType())) {
            return  QuickPayConstant.BizFuncCd.instalmentConsumption;
        }else if("3".equals(quickPayDTO.getQucikType())){
            return QuickPayConstant.BizFuncCd.preAuthorization;
        }
        return QuickPayConstant.BizFuncCd.fullConsumption;
    }

    private BizAssInf getBizAssInf(QuickPayDTO qucikPayDto) {
        if ("2".equals(qucikPayDto.getQucikType())) {
            return BizAssInf.BizAssInfBuilder.aBizAssInf()
                    .IPMode("0").IPNum(qucikPayDto.getNum())
                    .build();
        }
        return null;
    }


    private MsgHeader getMsgHeader(String trxtyp) {
        return MsgHeader.MsgHeaderBuilder.aMsgHeader().msgVer("1000")
                .drctn("11").issrId("05709200")
                .signSN("4038186043").sndDt(getNow())
                .signEncAlgo("0").trxtyp(trxtyp)
                .mDAlgo("0").encSN("4022714199").encKey("11111111").encAlgo("0").build();
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

    private String sign(Marshaller marshaller, QuickPayment response) throws JAXBException, IllegalAccessException {
        //JAVABean转XMLString
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(response,stringWriter);
        String src = stringWriter.toString();
        //加签
        String s = getSign(src);
        Sign sign1 = new Sign();
        sign1.setSignContext(s);
        return src + sign1.getSignResult();
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

    private QuickPayment postForEntity(String df,String msgTp) throws IllegalAccessException, JAXBException {
        //发送报文
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(50000);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Length", String.valueOf(df.length()));
        httpHeaders.set("Content-Type", "application/xml;charset=utf-8");
        httpHeaders.set("MsgTp",msgTp);
        httpHeaders.set("OriIssrId","05709200");
        HttpEntity<String> httpEntity = new HttpEntity<>(df, httpHeaders);
        ResponseEntity<String> result = restTemplate.postForEntity("http://172.16.151.148/", httpEntity, String.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            throw new IllegalAccessException("通信异常");
        }
        String resultStr =result.getBody();
        String signRegx = "(<root>)([\\s\\S]*?)(</root>)";
        String contentReg = "(\\{S:)([\\s\\S]*?)(})";
        String body = Pattern.compile(contentReg).matcher(resultStr).replaceAll("").trim();
        //String sign = Pattern.compile(signRegx).matcher(resultStr).replaceAll("").trim();
        //new Sign().getSignContextFromOrigin(sign);
        return (QuickPayment) JAXBContext.newInstance(QuickPayment.class).createUnmarshaller()
                .unmarshal(new StringReader(Objects.requireNonNull(body)));
    }

}
