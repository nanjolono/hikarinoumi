package com.nanjolono.payment.security.certification;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RsaCertUtilsTest {

    private static String path = ClassLoader.getSystemResource("").getPath();


    @Test
    void getPriKeyPkcs12() throws FileNotFoundException {
        String pfxkeyfile, keypwd, type;
        System.out.println(path);
        pfxkeyfile = path + "resources/eb882f6ef7f8a31c4d682611d31b8584b5030f83-sign.pfx";
        System.out.println(pfxkeyfile);
        keypwd = "111111";
        type = "PKCS12";
        assertNotNull(RsaCertUtils.getPriKeyPkcs12(pfxkeyfile
                ,keypwd,type));
    }

    @Test
    void getPriKeyJks() {
    }

    @Test
    void getPriKeyPkcs8() {
    }

    @Test
    void getPubKey() {
    }
}