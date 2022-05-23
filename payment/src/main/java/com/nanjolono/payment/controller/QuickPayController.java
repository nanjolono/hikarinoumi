package com.nanjolono.payment.controller;

import com.nanjolono.payment.bean.dto.ContractDto;
import com.nanjolono.payment.bean.dto.QueryOrderDTO;
import com.nanjolono.payment.bean.dto.SignDto;
import com.nanjolono.payment.response.QrcodePayResponse;
import com.nanjolono.payment.service.QuickPayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nanjolono.payment.bean.dto.QuickPayDTO;
import javax.xml.bind.JAXBException;

@RestController
public class QuickPayController {
    private static org.apache.log4j.Logger logger = Logger.getLogger(QuickPayController.class);
    @Autowired
    QuickPayService quickPayService;

    //签约触发短信
    @RequestMapping(value = "/sign",method = RequestMethod.POST)
    @ResponseBody
    public Object sign(@RequestBody SignDto signDto){
        try {
            signDto.checkParam();
            return quickPayService.sign(signDto);
        }catch (IllegalArgumentException e){
            return QrcodePayResponse.fail(e.getMessage());
        }catch (JAXBException e) {
            return QrcodePayResponse.fail("报文处理异常，请联系管理员");
        } catch (IllegalAccessException e) {
            return QrcodePayResponse.fail("参数异常,请稍后再试试");
        }
    }

    //签约
    @RequestMapping(value = "/contract",method = RequestMethod.POST)
    @ResponseBody
    public Object contract(@RequestBody ContractDto contractDto){
        try {
            contractDto.checkParam();
            return quickPayService.contract(contractDto);
        }catch (IllegalArgumentException e){
            return QrcodePayResponse.fail(e.getMessage());
        }catch (JAXBException e) {
            return QrcodePayResponse.fail("报文处理异常，请联系管理员");
        } catch (IllegalAccessException e) {
            return QrcodePayResponse.fail("参数异常,请稍后再试试");
        }
    }

    @RequestMapping(value = "/quickPay",method = RequestMethod.POST)
    @ResponseBody
    public Object quickPay(@RequestBody QuickPayDTO qucikPayDto){
        try {
            qucikPayDto.checkParam();
            return quickPayService.quickPay(qucikPayDto);
        }catch (IllegalArgumentException e){
            return QrcodePayResponse.fail(e.getMessage());
        }catch (JAXBException e) {
            return QrcodePayResponse.fail("报文处理异常，请联系管理员");
        } catch (IllegalAccessException e) {
            return QrcodePayResponse.fail("参数异常,请稍后再试试");
        }
    }


    @RequestMapping(value = "/queryTrans" , method = RequestMethod.POST)
    public Object queryTrans(QueryOrderDTO queryOrderQTO){
            queryOrderQTO.checkParam();
        try {
            return quickPayService.queryOrder(queryOrderQTO);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
