package com.nanjolono.payment.response;

/**
 * @author mazhao
 * @ClassName CommonConst
 * @Package com.sunyard.termbean
 * @Description 公用常量
 * @Date 2018/11/8 20:36
 * @Version V1.0
 */
public class CommonConst {

    //返回成功
    public static final String RET_SUCCESS = "00";
    //返回成功描述
    public static final String RET_SUCCESS_DESC = "调用成功";
    //失败
    public static final String RET_FAIL = "01";
    //返回成功描述
    public static final String RET_FAIL_DESC = "调用失败";
    //返回系统异常
    public static final String RET_ERROR = "02";
    //系统异常描述
    public static final String RET_ERROR_DESC = "系统异常";
    //支付失败
    public static final String RET_PAY_FAIL = "03";
    //系统异常描述
    public static final String RET_PAY_FAIL_DESC = "esb响应超时";
    //正在输入密码
    public static final String RET_USERPAYING = "04";
    //首次登录，请修改密码
    public static final String RET_FSTLOGIN_MODIFIERPWD = "03";
    //密码已过期，请重置密码
    public static final String EXPIRE_MODIFIERPWD = "04";
    //密码即将到期
    public static final String RET_SOON_EXPIRE = "05";

    //调用超时
    public static final String RET_TIMEOUT = "99";
    //调用超时描述
    public static final String RET_TIMEOUT_DESC = "调用超时";
    //调用超时
    public static final String ESB_RET_TIMEOUT = "60";
    //调用超时
    public static final String ESB_RET_TIMEOUT_DESC = "响应超时";


    //终端未签到
    public static final String RET_UNSIGNIN = "98";
    //终端未签到
    public static final String RET_UNSIGNIN_DESC = "终端未签到";

    //原交易不存在
    public static final String RET_UNEXSIT_ORIG_TRANS = "97";
    //原交易不存在
    public static final String RET_UNEXSIT_ORIG_TRANS_DESC = "原交易不存在";

    public static final String return_code = "01";


    /**
     * 
     * @param response
     * @param trans_info
     * @return
     */
    public static ResultPacket getResult(Object response, Object trans_info) {
        return new ResultPacket(response, trans_info);
    }

    /**
     * 
     * @param response
     * @return
     */
    public static ResultPacket getResult(Object response) {
        return new ResultPacket(response);
    }
}
