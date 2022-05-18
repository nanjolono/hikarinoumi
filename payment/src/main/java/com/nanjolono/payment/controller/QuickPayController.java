package com.nanjolono.payment.controller;

import com.nanjolono.payment.bean.dto.ContractDto;
import com.nanjolono.payment.bean.dto.SignDto;
import com.nanjolono.payment.service.QuickPayService;
import com.nanjolono.payment.utils.R;
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
    public R sign(@RequestBody SignDto signDto){
        try {
            signDto.checkParam();
            quickPayService.sign(signDto);
        }catch (IllegalArgumentException e){
            return R.error(e.getMessage());
        }catch (JAXBException e) {
            return R.error("报文处理异常，请联系管理员");
        } catch (IllegalAccessException e) {
            return R.error("参数异常,请稍后再试试");
        }
        return R.ok();
    }

    //签约
    @RequestMapping(value = "/contract",method = RequestMethod.POST)
    @ResponseBody
    public R contract(@RequestBody ContractDto contractDto){
        try {
            contractDto.checkParam();
            quickPayService.contract(contractDto);
        }catch (IllegalArgumentException e){
            return R.error(e.getMessage());
        }catch (JAXBException e) {
            return R.error("报文处理异常，请联系管理员");
        } catch (IllegalAccessException e) {
            return R.error("参数异常,请稍后再试试");
        }
        return R.ok();
    }

    @RequestMapping(value = "/quickPay",method = RequestMethod.POST)
    @ResponseBody
    public R quickPay(@RequestBody QuickPayDTO qucikPayDto){
        try {
            qucikPayDto.checkParam();
            quickPayService.quickPay(qucikPayDto);
        }catch (IllegalArgumentException e){
            return R.error(e.getMessage());
        }catch (JAXBException e) {
            return R.error("报文处理异常，请联系管理员");
        } catch (IllegalAccessException e) {
            return R.error("报文处理异常，请联系管理员");
        }
        return R.ok();
    }


}
