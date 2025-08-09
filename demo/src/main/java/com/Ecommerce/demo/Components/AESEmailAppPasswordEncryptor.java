package com.Ecommerce.demo.Components;

import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AESEmailAppPasswordEncryptor {
    private static final String ALGORITHM = "AES";
    private final SecretKey secretKey;

    public AESEmailAppPasswordEncryptor() throws Exception {
        this.secretKey = generateKey();
    }



    /**
     * Encrypts plaintext for database storage
     * @param plainText The sensitive data to encrypt (e.g., email app password)
     * @return Base64-encoded encrypted string
     */
    public String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts data retrieved from the database
     * @param encryptedText Base64-encoded encrypted string from DB
     * @return Original plaintext value
     */
    public String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Generate a random AES key (256-bit)
    private SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(256); // AES-256
        return keyGen.generateKey();
    }
}