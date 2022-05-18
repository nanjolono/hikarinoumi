package com.nanjolono.payment.controller;

import com.google.gson.Gson;
import com.nanjolono.payment.bean.dto.SignDto;
import com.nanjolono.payment.service.QuickPayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class QuickPayControllerTest {

    MockMvc mockMvc;

    @Autowired
    QuickPayService quickPayService;

    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(new QuickPayController()).build();
    }

    @Test
    void sign() throws Exception {
        SignDto signDto = new SignDto();
        signDto.setIdTp("01");
        signDto.setIdNo("220402199102030712");
        signDto.setRcverAcct("123123123");
        signDto.setRcverNm("张三");
        signDto.setMobNo("17654322345");
        String s = new Gson().toJson(signDto);
        mockMvc.perform(post("/sign")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(s).accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().is2xxSuccessful());
    }

}