package com.nanjolono.payment.bean.request.subbody;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class RskInf {

    //设备型号名称 Max256Text
    @XmlElement(name = "deviceMode")
    private String deviceMode;

    //设备语言，代码遵从 ISO639-3 标准 Max3Text
    @XmlElement(name = "deviceLanguage")
    private String deviceLanguage;

    //客户端IP地址 Max64Text
    @XmlElement(name = "sourceIP")
    private String sourceIP;

    //MAC地址 Max256Text 格式有以下两种:00-24-7E-0A-6C-2E、00247e0a6c2e
    @XmlElement(name = "MAC")
    private String MAC;

    //设备号 Max129Text 建议 PC 设备采集硬盘序列号、 安卓系统设备采集 IMEI、iOS 设 备采集 IDFV
    @XmlElement(name = "devId")
    private String devId;

    //GPS位置 Max32Text 经纬度，格式为纬度/经度，+表示 北纬、东经，-表示南纬、西经 举例:+37.12/-121.23 或者+37/- 121
    @XmlElement(name = "extensiveDeviceLocation")
    private String extensiveDeviceLocation;

    //SIM卡号 Max32Text 存储 11 位手机号，若存在 2 个通讯设备号码，则用逗号分隔
    @XmlElement(name = "deviceNumber")
    private String deviceNumber;

    //SIM卡数量 Max8Text 设备 sim 卡数量
    @XmlElement(name = "deviceSIMNumber")
    private String deviceSIMNumber;

    //账户ID  Max64Text 商户端用户支付时，如处于用户登录状态，提供商户系统中的用 户ID
    @XmlElement(name = "accountIDHash")
    private String accountIDHash;

    //风险评分 Max8Text 风险等级评分(0-1000 分)
    @XmlElement(name = "riskScore")
    private String riskScore;

    //原因码 Max100Text 风险评分的主要原因码
    @XmlElement(name = "riskReasonCode")
    private String riskReasonCode;


    //收单端用户注册日期 Max14Text 收单端用户注册时间，14 位时间字符 yyyymmddHHMMSS
    @XmlElement(name = "mchntUsrRgstrTm")
    private String mchntUsrRgstrTm;

    //收单端用户注册邮箱地址 Max64Text 收单端用户注册邮箱
    @XmlElement(name = "mchntUsrRgstrEmail")
    private String mchntUsrRgstrEmail;

    //收货省 Max4Text 收货地-省，注意需转换为银联清算地区代码
    @XmlElement(name = "rcvProvince")
    private String rcvProvince;

    //收货市 Max4Text 收货地-市，注意需转换为银联清算地区代码
    @XmlElement(name = "rcvCity")
    private String rcvCity;

    //商品类型 Max2Text 商品类别:虚拟(1)，非虚拟(2)，不确定(0)
    @XmlElement(name = "goodsClass")
    private String goodsClass;


}
