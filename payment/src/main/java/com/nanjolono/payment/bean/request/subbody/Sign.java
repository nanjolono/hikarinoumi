package com.nanjolono.payment.bean.request.subbody;

import org.apache.commons.lang.StringUtils;

public class Sign {

    private final String start = "{S:";

    private String signContext;

    private final String end = "}";

    public String getSignContext() {
        return signContext;
    }

    public void setSignContext(String signContext) {
        this.signContext = signContext;
    }

    public String getSignResult() throws IllegalAccessException {
        if (!StringUtils.isNotEmpty(signContext)) {
            throw new IllegalAccessException("sign is not allow empty");
        }
        return start + signContext + end;
    }
}
