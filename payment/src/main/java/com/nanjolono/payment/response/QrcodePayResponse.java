package com.nanjolono.payment.response;

/**
 * @Author 张昊坤
 * @Description 二维码交易服务调用返回
 * @Date 2021/3/16 9:06
 */
public class QrcodePayResponse {

    public static Response success(){
        Response response = new Response(CommonConst.RET_SUCCESS,CommonConst.RET_SUCCESS_DESC);
        return response;
    }

    public static Response success(Object transInfo){
        Response response = new Response(CommonConst.RET_SUCCESS,CommonConst.RET_SUCCESS_DESC,transInfo);
        return response;
    }


    public static Response fail(String desc){
        Response response = new Response(CommonConst.RET_FAIL,desc);
        return response;
    }

    public static Response error(){
        Response response = new Response(CommonConst.RET_ERROR,CommonConst.RET_ERROR_DESC);
        return response;
    }
}
