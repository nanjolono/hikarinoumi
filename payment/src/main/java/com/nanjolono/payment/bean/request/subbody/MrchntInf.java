package com.nanjolono.payment.bean.request.subbody;

import com.nanjolono.payment.bean.MsgBody;
import lombok.Data;

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


    public static final class MrchntInfBuilder {
        private MrchntInf mrchntInf;

        private MrchntInfBuilder() {
            mrchntInf = new MrchntInf();
        }

        public static MrchntInfBuilder aMrchntInf() {
            return new MrchntInfBuilder();
        }

        public MrchntInfBuilder MrchntNo(String MrchntNo) {
            mrchntInf.setMrchntNo(MrchntNo);
            return this;
        }

        public MrchntInfBuilder MrchntPltfrmNm(String MrchntPltfrmNm) {
            mrchntInf.setMrchntPltfrmNm(MrchntPltfrmNm);
            return this;
        }

        public MrchntInfBuilder SubMrchntNo(String SubMrchntNo) {
            mrchntInf.setSubMrchntNo(SubMrchntNo);
            return this;
        }

        public MrchntInfBuilder MrchntTpId(String MrchntTpId) {
            mrchntInf.setMrchntTpId(MrchntTpId);
            return this;
        }

        public MrchntInf build() {
            return mrchntInf;
        }
    }
}
