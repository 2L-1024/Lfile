package org.liaolong.base.utils;

/**
 * @author ll
 * @since 2025-03-01 16:44
 */
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AesUtils {
    private static final String AES = "AES";

    private static final String AES_CBC_PKCS5 = "AES/CBC/PKCS5Padding";

    /**
     * 生成随机 AES 密钥（适用于密钥存储）
     */
    public static String generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * AES 加密（CBC 模式）
     *
     * @param key 秘钥
     * @param data 待加密的明文
     * @return 加密后的密文
     */
    public static String encrypt(String key, String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5);
        IvParameterSpec iv = new IvParameterSpec(new byte[16]); // 默认全 0 IV
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * AES 解密（CBC 模式）
     *
     * @param key 秘钥
     * @param encryptedData 待加密的明文
     * @return 加密后的密文
     */
    public static String decrypt(String key, String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5);
        IvParameterSpec iv = new IvParameterSpec(new byte[16]);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String key = "";
        String text = "";

        String encrypted = encrypt(key, text);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}

