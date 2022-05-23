package com.nanjolono.payment.service.impl;

import com.nanjolono.payment.bean.QuickPay;
import com.nanjolono.payment.bean.dto.*;
import com.nanjolono.payment.bean.request.subbody.MrchntInf;
import com.nanjolono.payment.service.QuickPayService;
import com.nanjolono.payment.utils.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuickPayServiceImplTest {

    @Autowired
    QuickPayService quickPayService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;


    @Test
    void sign() throws JAXBException, IllegalAccessException {
        SignDto signDto = new SignDto();
        signDto.setIdTp("01");
        signDto.setIdNo("220402199102030712");
        signDto.setRcverAcct("6288888888888888");
        signDto.setRcverNm("张三");
        signDto.setMobNo("17654322345");
        quickPayService.sign(signDto);
    }

    @Test
    void contract() throws JAXBException, IllegalAccessException {
        ContractDto contractDto = new ContractDto();
        contractDto.setAuthMsg("123456");
        contractDto.setIdNo("220402199102030712");
        contractDto.setIdTp("01");
        contractDto.setMobNo("17654322345");
        contractDto.setRcverNm("张三");
        contractDto.setRcverAcct("6288888888888888");
        quickPayService.contract(contractDto);
    }

    @Test
    @DisplayName("全额消费")
    void quickPay() throws JAXBException, IllegalAccessException {
        QuickPayDTO quickPayDTO = new QuickPayDTO();
        quickPayDTO.setAmount("100.00");
        quickPayDTO.setQucikType("1");
        quickPayDTO.setTrxTp("08");
        quickPayDTO.setPyerAcctId("6288888888888888");
        quickPayService.quickPay(quickPayDTO);
    }

    @Test
    @DisplayName("退款")
    void refund() throws JAXBException, IllegalAccessException {
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setAmount("100.00");
        refundDTO.setTrxTrmTp("08");
        refundDTO.setOriTrxId("0405898KPLQ09182");
        refundDTO.setRefundType("5");
        quickPayService.refund(refundDTO);
    }

    @Test
    @DisplayName("预授权消费")
    void quickPayPreAuth() throws JAXBException, IllegalAccessException {
        QuickPayDTO quickPayDTO = new QuickPayDTO();
        quickPayDTO.setAmount("100.00");
        quickPayDTO.setQucikType("3");
        quickPayDTO.setTrxTp("08");
        quickPayDTO.setPyerAcctId("6288888888888888");
        quickPayService.quickPay(quickPayDTO);
    }

    @Test
    @DisplayName("分期消费")
    void quickPayPart() throws JAXBException, IllegalAccessException {
        QuickPayDTO quickPayDTO = new QuickPayDTO();
        quickPayDTO.setAmount("CNY100.00");
        quickPayDTO.setQucikType("2");
        quickPayDTO.setNum("12");
        quickPayDTO.setTrxTp("08");
        quickPayDTO.setPyerAcctId("6288888888888888");
        quickPayService.quickPay(quickPayDTO);
    }


    @Test
    @DisplayName("交易查询")
    void query() throws JAXBException, IllegalAccessException {
        QueryOrderDTO queryOrderDTO = new QueryOrderDTO();
        queryOrderDTO.setOriTrxId("0405898KPLQ09182");
        quickPayService.queryOrder(queryOrderDTO);
    }

    @Test
    @DisplayName("认证支付 - 触发短信")
    void authSMS() throws JAXBException, IllegalAccessException {
        AuthSMSDTO authSMSDTO = new AuthSMSDTO();
        authSMSDTO.setAmount("100.00");
        authSMSDTO.setIDNo("220403199602090818");
        authSMSDTO.setIDTp("08");
        authSMSDTO.setMobNo("17627890976");
        authSMSDTO.setRcverAcctId("6288888888888888");
        authSMSDTO.setRcverNm("张三");
        quickPayService.authSMS(authSMSDTO);
    }


    @Test
    @DisplayName("认证支付 - 快捷消费")
    void authPayMent() throws JAXBException, IllegalAccessException {
        AuthTransDTO authTransDTO = new AuthTransDTO();
        authTransDTO.setTrxTrmTp("08");
        authTransDTO.setPyerAcctId("12312312");
        authTransDTO.setAmount("100.00");
        quickPayService.authTrans(authTransDTO);
    }


    @Test
    void name() {
        System.out.println(redisTemplate.opsForValue().get("6288888888888888"));
    }
}