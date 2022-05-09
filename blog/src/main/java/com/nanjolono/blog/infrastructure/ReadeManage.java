package com.nanjolono.blog.infrastructure;

import com.nanjolono.blog.interfaces.ReadService;

public class ReadeManage {

    public ReadeManage Study(ReadService readService){
        readService.read();
        return this;
    }

}
