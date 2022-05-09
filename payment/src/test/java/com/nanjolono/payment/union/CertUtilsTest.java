package com.nanjolono.payment.union;

import com.nanjolono.payment.security.certification.RsaCertUtils;
import com.nanjolono.payment.security.rsa.RsaUtils;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CertUtilsTest {
    private static String path = ClassLoader.getSystemResource("").getPath();

    @Test
    void getPriKey() {
        String pfxkeyfile, keypwd, type;
        System.out.println(path);
        pfxkeyfile = path + "resources/eb882f6ef7f8a31c4d682611d31b8584b5030f83-sign.pfx";
        keypwd = "11111111";
        type = "PKCS12";

        PrivateKey privateKey = RsaCertUtils.getPriKeyPkcs12(pfxkeyfile, keypwd, type);
        byte[] res = Base64.encodeBase64(privateKey.getEncoded());

        System.out.println("prikey:\n" + new String(res));
    }

    @Test
    void getPubKey() {
        String filePath;
        filePath = path + "resources/test_rsa_qianming.cer";
        PublicKey publicKey = RsaCertUtils.getPubKey(filePath);
        byte[] res = Base64.encodeBase64(publicKey.getEncoded());
        System.out.println("pubkey f pubcert:\n" + new String(res));
    }

    @Test
    void loadPubkey() {
    }

    @Test
    void getPubKeyStr() {
    }

    @Test
    void getPriKeyStr() {
    }

    @Test
    void testGetPubKeyStr() {
    }
}