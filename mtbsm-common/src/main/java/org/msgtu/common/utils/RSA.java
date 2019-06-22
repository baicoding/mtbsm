package org.msgtu.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Wonshine on 2018-7-27.
 */
public class RSA {
    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    public static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return new BASE64Encoder().encode(bytes);
    }

    public static PublicKey getPublicKey(String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(keyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return new BASE64Encoder().encode(bytes);
    }

    public static PrivateKey getPrivateKeys(String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(keyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }



    /**
     * 公钥加密
     *
     * @param content
     * @param publicKey
     * @return
     */
    public static String encrpt(String content, String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IOException {
        PublicKey pk = getPublicKey(publicKey);
        return encrpt(content, pk);
    }

    /**
     * 公钥加密
     * @param content
     * @param publicKey
     * @return
     */
    public static String encrpt(String content, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content.getBytes());
        return new BASE64Encoder().encode(bytes);
    }

    public static String decrypt(String content, String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, InvalidKeyException, IOException {
        PrivateKey key=getPrivateKeys(privateKey);
        return decrypt(content,key);

    }

    public static String decrypt(String content, PrivateKey privateKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, IOException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
        return new String(bytes);
    }


    public static void main(String[] args) throws Exception{
        KeyPair kp=getKeyPair();
        String privateKey=getPrivateKey(kp);
        String publicKey=getPublicKey(kp);
        System.out.println("Private Key");
        System.out.println(privateKey);
        System.out.println("Public Key");
        System.out.println(publicKey);
        String context="Mr.Wang 8888";
        String ecCont=encrpt(context,publicKey);
        System.out.println("Encrpt");
        System.out.println(ecCont);
        String decC=decrypt(ecCont,privateKey);
        System.out.println("Decrypt");
        System.out.println(decC);
        if(context.equals(decC)){
            System.out.println(true);
        }else{
            System.out.println(false);
        }

    }


}
