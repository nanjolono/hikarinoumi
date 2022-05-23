package com.nanjolono.payment.utils;

public class Regx {

    public static final String idRegx = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9xX]$";


    public static final String signRegx = "(<root>)([\\s\\S]*?)(</root>)";


    public static final String contentRegx = "(\\{S:)([\\s\\S]*?)(})";

}
