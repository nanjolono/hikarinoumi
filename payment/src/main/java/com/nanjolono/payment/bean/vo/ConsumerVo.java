package com.nanjolono.payment.bean.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ConsumerVo implements Serializable {

    private String id;

    private String name;

    private String idNo;

    private String idTp;

    private String sgn;

    private String rcverAcctId;

    private String rcverNm;


}
