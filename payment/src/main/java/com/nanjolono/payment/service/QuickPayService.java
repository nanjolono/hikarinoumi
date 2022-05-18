package com.nanjolono.payment.service;

import com.nanjolono.payment.bean.QuickPayment;
import com.nanjolono.payment.bean.dto.*;
import com.nanjolono.payment.utils.R;

import javax.xml.bind.JAXBException;

public interface QuickPayService {

    /**
     * 协议支付 - 填写信息
     * @param signDto
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object sign(SignDto signDto) throws JAXBException, IllegalAccessException;

    /**
     * 协议支付 - 签约
     * @param contractDto
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object contract(ContractDto contractDto) throws JAXBException, IllegalAccessException;

    /**
     * 协议支付 - 支付
     * @param qucikPayDto
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object quickPay(QuickPayDTO qucikPayDto) throws JAXBException, IllegalAccessException;

    /**
     * 协议支付 - 退款
     * @param refundDTO
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object refund(RefundDTO refundDTO) throws JAXBException, IllegalAccessException;

    /**
     * 协议支付 - 订单查询
     * @param queryOrder
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object queryOrder(QueryOrder queryOrder) throws JAXBException, IllegalAccessException;

    /**
     * 协议支付 - 预授权撤销
     * @param queryOrder
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object preAhtuCancel(QueryOrder queryOrder) throws JAXBException, IllegalAccessException;

    /**
     * 协议支付 - 预授权完成
     * @param queryOrder
     * @return
     * @throws JAXBException
     * @throws IllegalAccessException
     */
    Object preAhtuDone(QueryOrder queryOrder) throws JAXBException, IllegalAccessException;

}
