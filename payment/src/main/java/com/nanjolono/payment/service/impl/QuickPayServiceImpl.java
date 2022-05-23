package com.nanjolono.payment.service.impl;


import com.nanjolono.payment.bean.MsgHeader;
import com.nanjolono.payment.bean.QuickPayment;
import com.nanjolono.payment.bean.dto.*;
import com.nanjolono.payment.bean.request.Contract;
import com.nanjolono.payment.bean.request.subbody.*;
import com.nanjolono.payment.bean.vo.ConsumerVo;
import com.nanjolono.payment.constant.QuickPayConstant;
import com.nanjolono.payment.response.QrcodePayResponse;
import com.nanjolono.payment.security.certification.RsaCertUtils;
import com.nanjolono.payment.security.cupsec.CupSec;
import com.nanjolono.payment.service.QuickPayService;
import com.nanjolono.payment.utils.Regx;
import org.apache.commons.lang.StringUtils;
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
import java.util.Objects;
import java.util.UUID;
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
                        .verifyInfo(QuickPayConstant.BizTpCd.Service.accountService
                                ,QuickPayConstant.BizFuncCd.fullConsumption)
                        .bizAssInf(new BizAssInf())
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
            return QrcodePayResponse.error();
        }
        redisTemplate.opsForValue()
                .set("SmsKey:"+signDto.getRcverAcct()
                        ,result.getMsgBody().getRcverInf().getSmskey(),1000,TimeUnit.DAYS);
        return QrcodePayResponse.success();
    }



    @Override
    public Object contract(ContractDto contractDto) throws JAXBException, IllegalAccessException {
        QuickPayment entity = QuickPayment.QuickPaymentBuilder.aQuickPayment(getMsgHeader(QuickPayConstant.TrxTpCd.SignTrans.CONTRACT_PAYMENT),Contract.ContractBuilder
                .verifyInfo(QuickPayConstant.BizTpCd.Service.accountService
                        ,QuickPayConstant.BizFuncCd.fullConsumption)
                .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf(contractDto.getRcverAcct(), contractDto.getRcverNm())
                        .IDNo(contractDto.getIdNo())
                        .IDTp(contractDto.getIdTp())
                        .MobNo(contractDto.getMobNo())
                        .AuthMsg(contractDto.getAuthMsg())
                        .Smskey(getSgnNo(contractDto.getIdNo()).toString()).build())
                .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0409PLMN9012ER45", getNow()).build())
                .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("1231D312312", "1231232DQW1").build())
                .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf().OriTrxId("0409PLMN9012ER45").build())
                .build()
        ).build();
        QuickPayment result = postForEntity(sign(getMarshaller(), entity), "0201");
        if (!"00000000".equals(Objects.requireNonNull(result.getMsgBody().getSysRtnInf()).getSysRtnCd())) {
            return QrcodePayResponse.error();
        }
        ConsumerVo consumerVo = new ConsumerVo();
        consumerVo.setId(UUID.randomUUID().toString().replace("-",""));
        consumerVo.setIdNo(contractDto.getIdNo());
        consumerVo.setIdTp(contractDto.getIdTp());
        consumerVo.setName(contractDto.getRcverNm());
        consumerVo.setRcverAcctId(contractDto.getRcverAcct());
        consumerVo.setSgn(result.getMsgBody().getBizInf().getSgnNo());
        saveUser(consumerVo);
        saveCardList(contractDto);
        return QrcodePayResponse.success();
    }

    private void saveCardList(ContractDto contractDto) {
        redisTemplate.opsForValue().set("UserAccount:"+ contractDto.getIdNo(), contractDto.getRcverAcct(),1000,TimeUnit.DAYS);
    }

    private void saveUser(ConsumerVo consumerVo) {
        redisTemplate.opsForValue().set(
                "Consumer:"+ consumerVo.getIdNo(),
                consumerVo,1000, TimeUnit.DAYS);
    }

    @Override
    public Object quickPay(QuickPayDTO qucikPayDto) throws JAXBException, IllegalAccessException {
        QuickPayment entity = QuickPayment.QuickPaymentBuilder.aQuickPayment(getMsgHeader(QuickPayConstant.TrxTpCd.Debit.TRANS_PROTOCOL),
                Contract.ContractBuilder.verifyInfo(QuickPayConstant.BizTpCd.Service.accountService
                                , getBizFunc(qucikPayDto.getQucikType()))
                        .bizAssInf(getBizAssInf(qucikPayDto))
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405898KPLQ09182", getNow())
                                .TrxAmt("CNY"+qucikPayDto.getAmount())
                                .TrxTrmTp(qucikPayDto.getTrxTp())
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId(qucikPayDto.getPyerAcctId())
                                .build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                .PyeeIssrId("asdasd") //接收方账号
                                .PyeeAcctIssrId("asdasd") //接收方账号
                                .build())
                        .channelIssInf(ChannelIssInf.ChannelIssInfBuilder.aChannelIssInf()
                                .ChannelIssrId("01ADS523123")  //渠道号
                                .SgnNo(getSgnNo(qucikPayDto.getPyerAcctId()).toString())
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
        redisTemplate.opsForValue().set(
                "Trans:"+result.getMsgBody().getTrxInf().getTrxId(),
                entity.getMsgBody().getTrxInf(),2,TimeUnit.DAYS);
        if (!"00000000".equals(Objects.requireNonNull(result.getMsgBody().getSysRtnInf()).getSysRtnCd())) {
            return QrcodePayResponse.error();
        }
        return QrcodePayResponse.success();
    }

    private Object getSgnNo(String qucikPayDto) {
        Object value = redisTemplate.opsForValue().get("SmsKey:"+qucikPayDto);
        if(value == null){
            throw new IllegalArgumentException("账号号码不存在");
        }
        return value;
    }

    @Override
    public Object refund(RefundDTO refundDTO) throws JAXBException, IllegalAccessException {
        TrxInf d = (TrxInf)redisTemplate.opsForValue().get("Trans:"+refundDTO.getOriTrxId());
        if(null !=d && StringUtils.isEmpty(d.getTrxAmt()) && !("CNY"+d.getTrxAmt()).equals(refundDTO.getAmount())){
            throw new IllegalArgumentException("退款金额与原订单金额不一致");
        }
        QuickPayment build = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                getMsgHeader(QuickPayConstant.TrxTpCd.Credit.refund),
                Contract.ContractBuilder.verifyInfo(QuickPayConstant.BizTpCd.Service.systemService
                                , getBizFunc(refundDTO.getRefundType()))
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405899KPLQ09183",getNow())
                                .TrxAmt("CNY"+refundDTO.getAmount())
                                .TrxTrmTp(refundDTO.getTrxTrmTp())
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctIssrId("123123")
                                .PyerAcctIssrId("123123")
                                .PyeeIssrId("123123")
                                .build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntTpId("0209")  //商户类型
                                .MrchntNo("333T12F222D3123")  //商户号
                                .MrchntPltfrmNm("123123123")  //商户名称
                                .build())
                        .oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf()
                                .OriTrxId(d.getTrxId())
                                .OriTrxAmt(d.getTrxAmt())
                                .OriOrdrId("123123123")
                                .OriTrxDtTm(d.getTrxDtTm())
                                .OriTrxTp("1001")
                                .OriBizFunc(QuickPayConstant.BizFuncCd.preAuthorization)
                                .build())
                        .build()
        ).build();
        postForEntity(sign(getMarshaller(),build),QuickPayConstant.TrxTpCd.Credit.refund);
        return null;
    }

    @Override
    public Object queryOrder(QueryOrderDTO queryOrderDTO) throws JAXBException, IllegalAccessException {
        TrxInf d = (TrxInf)redisTemplate.opsForValue().get("Trans:"+queryOrderDTO.getOriTrxId());
        QuickPayment entity = QuickPayment.QuickPaymentBuilder
                .aQuickPayment(getMsgHeader(QuickPayConstant.TrxTpCd.transStatus.transStatusQuery),
                        Contract.ContractBuilder.verifyInfo(QuickPayConstant.BizTpCd.Service.systemService,
                                        QuickPayConstant.BizFuncCd.fullConsumption)
                                .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf(
                                        "0405899KPLQ09183",getNow()
                                ).build()
                                ).oriTrxInf(OriTrxInf.OriTrxInfBuilder.anOriTrxInf()
                                        .OriTrxId(Objects.requireNonNull(d).getTrxId())
                                        .OriTrxDtTm(d.getTrxDtTm())
                                        .OriTrxTp(QuickPayConstant.TrxTpCd.Debit.TRANS_PROTOCOL)
                                        .OriBizTp("300001")
                                        .build())
                                .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("123123", "1231232").build())
                                .build()
                ).build();
        QuickPayment result = postForEntity(sign(getMarshaller(), entity), QuickPayConstant.TrxTpCd.transStatus.transStatusQuery);
        return null;
    }

    @Override
    public Object preAhtuCancel(QueryOrderDTO queryOrderDTO) throws JAXBException, IllegalAccessException {
        return null;
    }

    @Override
    public Object preAhtuDone(QueryOrderDTO queryOrderDTO) throws JAXBException, IllegalAccessException {
        return null;
    }

    @Override
    public Object authSMS(AuthSMSDTO authSMSDTO) throws JAXBException, IllegalAccessException {
        QuickPayment build = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                getMsgHeader(QuickPayConstant.TrxTpCd.AccountInfo.VERIFY_PAY), Contract.ContractBuilder.verifyInfo(
                        QuickPayConstant.BizTpCd.Service.accountService
                                , QuickPayConstant.BizFuncCd.instalmentConsumption)
                                .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405899KPLQ09183",getNow())
                                        .TrxAmt("CNY"+authSMSDTO.getAmount())
                                        .build())
                                .rcverInf(RcverInf.RcverInfBuilder.verifyInfoRcverInf(authSMSDTO.getRcverAcctId(),authSMSDTO.getRcverNm())
                                        .IDTp(authSMSDTO.getIDTp())
                                        .IDNo(authSMSDTO.getIDNo())
                                        .MobNo(authSMSDTO.getMobNo())
                                        .build())
                                .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("123123", "1231232").build())
                                .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                        .MrchntTpId("0209")  //商户类型
                                        .MrchntNo("333T12F222D3123")  //商户号
                                        .MrchntPltfrmNm("123123123")  //商户名称
                                        .build())
                        .build()
        ).build();
        postForEntity(sign(getMarshaller(),build),QuickPayConstant.TrxTpCd.AccountInfo.VERIFY_PAY);
        return null;
    }

    @Override
    public Object authTrans(AuthTransDTO authTransDTO) throws JAXBException, IllegalAccessException {
        QuickPayment build = QuickPayment.QuickPaymentBuilder.aQuickPayment(
                getMsgHeader(QuickPayConstant.TrxTpCd.Debit.Certification),
                Contract.ContractBuilder.verifyInfo("100001",getBizFunc(authTransDTO.getQuickType()))
                        .bizAssInf(getBizAssInf(authTransDTO))
                        .trxInf(TrxInf.TrxInfBuilder.verifyInfoTrxInf("0405899KPLQ09182",getNow())
                                .TrxAmt("CNY"+authTransDTO.getAmount())
                                .TrxTrmTp(authTransDTO.getTrxTrmTp())
                                .build())
                        .pyerInf(PyerInf.PyerInfBuilder.aPyerInf()
                                .PyerAcctId("123123123")
                                .AuthMsg(authTransDTO.getAuthMsg())
                                .Smskey("20220523113032000126")
                                .PyerAcctIssrId(authTransDTO.getPyerAcctId())
                                .build())
                        .pyeeInf(PyeeInf.PyeeInfBuilder.aPyeeInf()
                                .PyeeIssrId("123123123")
                                .PyeeAcctIssrId("123123123")
                                .build())
                        .ordrInf(OrdrInf.OrdrInfBuilder.anOrdrInf()
                                .OrdrId("123123123").build())
                        .sderInf(SderInf.SderInfBuilder.verifyInfoSderInf("123123", "1231232").build())
                        .mrchntInf(MrchntInf.MrchntInfBuilder.aMrchntInf()
                                .MrchntTpId("0209")  //商户类型
                                .MrchntNo("333T12F222D3123")  //商户号
                                .MrchntPltfrmNm("123123123")  //商户名称
                                .build())
                        .build()
        ).build();
        postForEntity(sign(getMarshaller(),build),QuickPayConstant.TrxTpCd.Debit.Certification);
        return null;
    }

    private String getBizFunc(String quickType) {
        if ("2".equals(quickType)) {
            return  QuickPayConstant.BizFuncCd.instalmentConsumption;
        }else if("3".equals(quickType)){
            return QuickPayConstant.BizFuncCd.preAuthorization;
        }else if("4".equals(quickType)){
            return QuickPayConstant.BizFuncCd.refund;
        }else if("5".equals(quickType)){
            return QuickPayConstant.BizFuncCd.fundUnfreeze;
        }else if("6".equals(quickType)){
            return QuickPayConstant.BizFuncCd.preAuthorizationCompleted;
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

    private BizAssInf getBizAssInf(AuthTransDTO authTransDTO) {
        if ("2".equals(authTransDTO.getQuickType())) {
            return BizAssInf.BizAssInfBuilder.aBizAssInf()
                    .IPMode("0").IPNum(authTransDTO.getNum())
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
        ResponseEntity<String> result = restTemplate.postForEntity("http://172.16.151.157", httpEntity, String.class);
        if (!result.getStatusCode().is2xxSuccessful()) {
            throw new IllegalAccessException("通信异常");
        }
        String resultStr =result.getBody();
        String body = Pattern.compile(Regx.contentRegx).matcher(resultStr).replaceAll("").trim();
        //String sign = Pattern.compile(signRegx).matcher(resultStr).replaceAll("").trim();
        //new Sign().getSignContextFromOrigin(sign);
        return (QuickPayment) JAXBContext.newInstance(QuickPayment.class).createUnmarshaller()
                .unmarshal(new StringReader(Objects.requireNonNull(body)));
    }

}
