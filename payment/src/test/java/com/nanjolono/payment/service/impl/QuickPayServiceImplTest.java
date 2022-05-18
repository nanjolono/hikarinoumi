package com.nanjolono.payment.service.impl;

import com.nanjolono.payment.bean.QuickPay;
import com.nanjolono.payment.bean.dto.ContractDto;
import com.nanjolono.payment.bean.dto.QuickPayDTO;
import com.nanjolono.payment.bean.dto.SignDto;
import com.nanjolono.payment.service.QuickPayService;
import com.nanjolono.payment.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

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
    void quickPay() throws JAXBException, IllegalAccessException {
        QuickPayDTO quickPayDTO = new QuickPayDTO();
        quickPayDTO.setAmount("CNY100.00");
        quickPayDTO.setQucikType("2");
        quickPayDTO.setNum("12");
        quickPayDTO.setTrxTp("08");
        quickPayDTO.setPyerAcctId("6288888888888888");
        quickPayService.quickPay(quickPayDTO);
    }

    @Test
    void name() {
        System.out.println(redisTemplate.opsForValue().get("6288888888888888"));
    }
}