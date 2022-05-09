package com.nanjolono.payment.bean.request.subbody;

import com.nanjolono.payment.bean.request.MsgBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class MrchntInf extends MsgBody {

    //商户编码-特约商户编码，仅限字母和数字
    @XmlElement(name = "MrchntNo")
    private String MrchntNo;

    //商户类别-特约商户所属的商户类别代码
    @XmlElement(name = "MrchntPltfrmNm")
    private String MrchntPltfrmNm;

    //商户名称-用于标识商户的名称
    @XmlElement(name = "SubMrchntNo")
    private String SubMrchntNo;

    //商品类别-特约商户所属的商户类别代码
    @XmlElement(name="MrchntTpId")
    private String MrchntTpId;

}
