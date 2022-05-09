package com.nanjolono.blog.interfaces;

public class EnglishRead implements ReadService{
    @Override
    public void read() {
        System.out.println("English read");
    }

    @Override
    public void upgrade() {
        System.out.println("English upgrade");
    }
}
