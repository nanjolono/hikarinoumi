package com.nanjolono.blog.controller;

import com.nanjolono.blog.infrastructure.ReadeManage;
import com.nanjolono.blog.interfaces.ChineseRead;
import com.nanjolono.blog.interfaces.EnglishRead;
import com.nanjolono.blog.interfaces.ReadService;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class BlogControllerTest {

    @Test
    void getSth() {
        assertThatIllegalArgumentException().isThrownBy(
                () ->Assert.isTrue(false, "enigma")
        ).withMessageMatching("123");
    }

    @Test
    void readTest(){
        ReadeManage readeManage = new ReadeManage();
        ChineseRead chineseRead = new ChineseRead();
        EnglishRead englishRead = new EnglishRead();
        readeManage.Study(englishRead);
    }
}