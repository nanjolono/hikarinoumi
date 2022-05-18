package com.nanjolono.payment.response;

/**
 * @author mazhao
 * @ClassName ResultPacket
 * @Package com.sunyard.termbean
 * @Description 返回对象
 * @Date 2018/11/8 20:37
 * @Version V1.0
 */
public class ResultPacket {

    /**
     * @FIELD: TODO
     */
    private Object response;

    /**
     * @FIELD: TODO
     */
    private Object trans_info;

    /**
     * @FIELD: TODO
     */
    public ResultPacket() {
    }
    
    /**
     * 
     * @param response
     * @param trans_info
     */
    public ResultPacket(Object response, Object trans_info) {
        this.response = response;
        this.trans_info = trans_info;
    }
    /**
     * 
     * @param response
     */
    public ResultPacket(Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getTrans_info() {
        return trans_info;
    }

    public void setTrans_info(Object trans_info) {
        this.trans_info = trans_info;
    }
}
