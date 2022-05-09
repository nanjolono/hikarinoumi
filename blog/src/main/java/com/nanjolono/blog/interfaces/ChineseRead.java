package com.nanjolono.blog.interfaces;

public class ChineseRead implements ReadService{
    @Override
    public void read() {
        System.out.println("Chinese read");
    }

    @Override
    public void upgrade() {
        System.out.println("Chinese upgrade");
    }
}
