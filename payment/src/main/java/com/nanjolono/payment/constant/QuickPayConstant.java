package com.nanjolono.payment.constant;

public interface QuickPayConstant {

    interface TrxTpCd {

        interface AccountInfo {
            //账户服务业务 账户信息验证交易 协议支付签约触发短信
            String CONTRACT = "0001";

            //账户服务业务 账户信息验证交易 借记转账签约触发短信
            String TRANSFER = "0002";

            //账户服务业务 账户信息验证交易 认证支付触发短信
            String VERIFY_PAY = "0003";

            //账户服务业务 账户信息验证交易 账户信息验证
            String VERIFY_INFO = "0101";
        }

        interface SignTrans {
            //账户服务业务 签约交易 协议支付签约
            String CONTRACT_PAYMENT = "0201";

            //账户服务业务 签约交易 借记转账支付签约
            String DEBIT_TRANSFOR_PAYMENT = "0202";

            //账户服务业务 签约交易 网关签约
            String GATEWAY_SIGN = "0203";

            //账户服务业务 签约交易 签约受理
            String SIGNATURE = "0204";
        }

        interface DissignTrans {
            //账户服务业务 签约交易 解约
            String TRX_TP_CD_DISSIGN_TRANS_DISCONTRACT_PAYMENT = "0301";

            //账户服务业务 签约交易 借记转账解约
            String TRX_TP_CD_DISSIGN_TRANS_DEBITDISCONTRACT_PAYMENT = "0302";

            //账户服务业务 签约交易 解约通知
            String TRX_TP_CD_DISSIGN_TRANS_DISCONTRACT_NOTICE = "0303";
        }

        interface Balance {
            //账户服务业务 签约交易 余额查询
            String TRX_TP_CD_BALANCE_QUERY_TRANCE = "0401";
        }

        //营销活动
        interface Aactive {
            //账户服务业务 签约交易 营销信息查询
            String INFO_QUERY = "0601";
        }

        //借记交易
        interface Debit {
            //协议支付
            String TRANS_PROTOCOL = "1001";

            //认证支付
            String Certification = "1002";

            //借记转账
            String transfer = "1003";

            //个人网关支付
            String PERSONAL_GATEWAY_PAY = "1005";

            //个人网关预授权完成
            String PRE_AUTHORIZATION = "1006";

            //企业网关支付
            String ENTERPRISE_GATEWAY = "1011";

            //企业网关无跳转支付
            String ENTERPRISE_GATEWAY_WITHOUT_JUMP = "1012";
        }

        //贷记业务
        interface Credit {
            //退货
            String refund = "1101";
            //付款
            String credit = "2001";
        }

        //通知交易
        interface NoticeTrans {
            //交易终态通知SW-IS
            String endStatusSWIS = "3002";
            //交易终态通知SW-AC
            String endStatusSWAC = "3012";
            //货记终态通知
            String creaditStatus = "3004";
            //交易状态通知IS-SW
            String statusISAC = "3005";
            //交易状态通知SW-AC
            String statusSWAC = "3015";
            //交易结果通知SW-IS
            String statusResultSWIS = "3006";
            //交易结果通知SW-AC
            String statusResultSWAC = "3016";
        }

        //交易状态查询
        interface transStatus {
            //交易状态信息查询
            String transStatusQuery = "3101";
            //交易状态信息确认
            String transStatusConfirm = "3102";
        }


        //营销承兑
        interface activeAcceptance {
            //营销承兑
            String activeAcceptance = "3201";
        }

        //凭证获取
        interface credentialAcquisition {
            //凭证获取
            String credentialAcquisition = "3301";
        }

        //前台交易
        interface receptionTrans {
            //网关跳转
            String gatewayJump = "0501";
            //网关回跳
            String gatewayBounce = "0502";
            //受理跳转
            String acceptJump = "0503";
        }

        //网络管理
        interface networkManage {
            //签退
            String SignOut = "4001";
            //签到
            String SignIn = "4002";
            //更新证书
            String UpadteCert = "4003";
        }
    }


    interface BizTpCd {
        //@TODO
        interface Service {
            //账户类
            String accountService = "300001";
            //系统类
            String systemService = "300002";
            //管理类
            String manageService = "300003";

        }
    }

    interface BizFuncCd {
        //全额消费
        String fullConsumption = "111011";
        //分期消费
        String instalmentConsumption = "112011";
        //预授权
        String preAuthorization = "111021";
        //预授权完成
        String preAuthorizationCompleted = "111031";
        //全额付款
        String fullPayment = "311011";
        //退款
        String refund = "411011";
        //资金解冻
        String fundUnfreeze = "411021";
    }

    //账户类型
    interface AcctTpCd {
        //个人银行借记账户
        String debitAccount = "01";
        //个人银行贷记账户
        String creaditAccount = "02";
        //个人银行准贷记账户
        String quasiCredit = "03";
        //银行预付费账户
        String prepaidAccount = "05";
        //个人支付账户
        String personalPaymentAccount = "10";
        //单位支付账户
        String unitPaymentAccount = "11";
        //对公银行账户
        String corporateBankAccount = "20s";
    }

    //对公银行账户类型
    interface CorBnkAcctTpCd {
        //基本账户
        String basicAccount = "21";
        //一般账户
        String generalAccount = "22";
        //临时账户
        String temporaryAccount = "23";
        //专用账户
        String dedicatedAccount = "24";
    }

    //账户等级
    interface AcctLvlCd {
        //信用卡账户
        String creditCardAccount = "0";
        //一类账户
        String firstClassAccount = "1";
        //二类账户
        String secondClassAccount = "2";
        //三类账户
        String thirdClassAccount = "3";
    }

    //交易状态
    interface TrxStatusCd{
        //交易成功
        String transSuccess = "";
        //交易失败
        String transFail="";
        //交易处理中
        String transactionProcessing="";
        //交易有缺陷成功
        String defectiveTransactionSucceeds="";
    }

    //终端类型
    interface TrxTrmTpCd{
        //电脑
        String computer = "7";
        //手机
        String mobile = "8";
        //平板
        String pad = "10";
        //可穿戴设备
        String wearableDevice="18";
        //数字电视
        String digitalTelevision="19";
        //其他
        String other = "00";
    }
}
