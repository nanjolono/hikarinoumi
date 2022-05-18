package com.nanjolono.payment.response;/**
 * @Title: ResponseResp
 * @ProjectName nbyh_cts
 * @Description: TODO
 * @author zhix.huang
 * @date 2018/8/2817:23
 */

/**
 * @author zhix.huang
 * @ClassName ResponseResp
 * @DEescription 返回对象
 * @Date 2018/8/28
 **/
public class ResponseResp {
    /**
     * @FIELD: TODO
     */
    private String return_code;
    /**
     * @FIELD: TODO
     */
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    
    /**
     * 构造方法
     */
    public ResponseResp() {
    	super();
    }
    /**
     * 
     * @param return_code
     * @param return_msg
     */
    public ResponseResp(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {
        return "ResponseResp{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                '}';
    }
}
