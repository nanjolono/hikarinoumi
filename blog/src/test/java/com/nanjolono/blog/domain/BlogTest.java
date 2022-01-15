package com.nanjolono.blog.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BlogTest {

    @Test()
    @DisplayName("属性值注入是否成功")
    public void isEuqal(){
        Blog build = new Blog.Builder("百年孤独")
                .author("张三").context("success").build();
        assertEquals(build.getAuthor(),"张三");
        assertEquals(build.getTitle(),"百年孤独");
        assertEquals(build.getContext(),"success");
    }

    @Test
    @DisplayName("lambda函数测试")
    public void apply(){
        Blog build = new Blog.Builder("zhangsan")
                .author("lisi").build();

        build.apply((d)->{
            return d.substring(0,2);
        });

        System.out.println(build.getAuthor());
    }
}