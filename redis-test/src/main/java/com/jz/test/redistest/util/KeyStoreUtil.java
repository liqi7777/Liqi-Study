package com.jz.test.redistest.util;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class KeyStoreUtil {
    public static KeyStore loadKeyStore(String keyStorePath, String keyStorePassword) throws Exception {
        FileInputStream is = new FileInputStream(keyStorePath);
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(is, keyStorePassword.toCharArray());
        return keystore;
    }

    public static PrivateKey getPrivateKey(KeyStore keystore, String alias, String keyPassword) throws Exception {
        return (PrivateKey) keystore.getKey(alias, keyPassword.toCharArray());
    }

    public static PublicKey getPublicKey(KeyStore keystore, String alias) throws Exception {
        Certificate cert = keystore.getCertificate(alias);
        return cert.getPublicKey();
    }

    public static RSAPrivateKey getRSAPrivateKey(String keyStoreFile, String keyStorePassword, String alias, String keyPassword) throws Exception {
        FileInputStream is = new FileInputStream(keyStoreFile);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, keyStorePassword.toCharArray());

        KeyStore.PasswordProtection keyPasswordProtection = new KeyStore.PasswordProtection(keyPassword.toCharArray());
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keystore.getEntry(alias, keyPasswordProtection);

        return (RSAPrivateKey) privateKeyEntry.getPrivateKey();
    }

    public static RSAPublicKey getRSAPublicKey(String keyStoreFile, String keyStorePassword, String alias) throws Exception {
        FileInputStream is = new FileInputStream(keyStoreFile);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, keyStorePassword.toCharArray());

        Certificate cert = keystore.getCertificate(alias);
        return (RSAPublicKey) cert.getPublicKey();
    }

}
