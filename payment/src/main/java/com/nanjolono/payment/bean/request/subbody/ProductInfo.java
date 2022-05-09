package com.nanjolono.payment.bean.request.subbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInfo {

    //产品类型 - 发起方可选，银联必校验并补填 给接收方，默认 00000000。具体 取值由不同业务产品定义
    @XmlElement(name = "ProductTp")
    private String ProductTp;

    //产品辅助信息 具体用法取值与产品类型保持一致，由不同业务产品定义 Max120Text
    @XmlElement(name = "ProductAssInformation")
    private String ProductAssInformation;

}
