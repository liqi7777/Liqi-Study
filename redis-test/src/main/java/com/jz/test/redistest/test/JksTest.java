package com.jz.test.redistest.test;

import cn.hutool.Hutool;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jz.test.redistest.util.KeyStoreUtil;
import org.bouncycastle.crypto.tls.SignatureAlgorithm;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.cert.Certificate;


/**
 * @author liqi
 * create  2024/7/16 9:52 上午
 */
public class JksTest {

    @Test
    public void test() throws Exception {
        KeyStore keyStore = KeyStoreUtil.loadKeyStore("/Users/liqi/Desktop/liqi.jks", "123456");
        PrivateKey privateKey = KeyStoreUtil.getPrivateKey(keyStore, "liquid", "123456");
        PublicKey liqi1 = KeyStoreUtil.getPublicKey(keyStore, "liquid");
        System.out.println(liqi1);

    }

    /**
     * 生效方案(**)
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String keyStoreFile = "/Users/liqi/Desktop/soms-stormspr-remote/redis-test/src/main/resources/liqi.jks";
        String keyStorePassword = "123456";
        String alias = "liquid";
        String keyPassword = "123456";

        RSAPrivateKey privateKey = KeyStoreUtil.getRSAPrivateKey(keyStoreFile, keyStorePassword, alias, keyPassword);
        RSAPublicKey publicKey = KeyStoreUtil.getRSAPublicKey(keyStoreFile, keyStorePassword, alias);
        String jwtToken = generateToken(publicKey, privateKey);
        System.out.println("Generated JWT Token: " + jwtToken);
    }

    @Test
    public void test3() throws Exception{
        String keyStoreFile = "/Users/liqi/Desktop/liqi.jks";
        String keyStorePassword = "123456";
        String alias = "liquid";
        String keyPassword = "123456";
        String outputFilePath = "/Users/liqi/Desktop/liqipublic.txt";

        RSAPrivateKey privateKey = KeyStoreUtil.getRSAPrivateKey(keyStoreFile, keyStorePassword, alias, keyPassword);
        RSAPublicKey publicKey = KeyStoreUtil.getRSAPublicKey(keyStoreFile, keyStorePassword, alias);

        writePublicKeyToFile(publicKey, outputFilePath);

    }

    public static void writePublicKeyToFile(RSAPublicKey publicKey, String filePath) throws IOException {
        String publicKeyContent = "-----BEGIN PUBLIC KEY-----\n"
                + Base64.getMimeEncoder().encodeToString(publicKey.getEncoded())
                + "\n-----END PUBLIC KEY-----";

        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(publicKeyContent);
        }
    }

    public static String generateToken(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        String token = JWT.create()
                .withIssuer("issuer")
                .withSubject("subject")
                .withAudience("audience")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hour expiration
                .withClaim("customClaim", "customValue")
                .sign(algorithm);

        return token;
    }

}
